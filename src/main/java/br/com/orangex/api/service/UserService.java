package br.com.orangex.api.service;

import br.com.orangex.api.dto.GetUserDTO;
import br.com.orangex.api.dto.CreateUserDTO;
import br.com.orangex.api.dto.UpdateUserDTO;
import br.com.orangex.api.exception.AuthException;
import br.com.orangex.api.exception.NotFoundException;
import br.com.orangex.api.exception.UniqueConstraintViolatedException;
import br.com.orangex.api.model.User;
import br.com.orangex.api.model.UserRole;
import br.com.orangex.api.repository.PostRepository;
import br.com.orangex.api.repository.UserRepository;
import br.com.orangex.api.util.CurrentUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public GetUserDTO create(CreateUserDTO user){

        try{
            User newUser = new User();
            BeanUtils.copyProperties(user, newUser);
            String passwordEncoded = passwordEncoder.encode(newUser.getPassword());
            newUser.setPassword(passwordEncoded);
            newUser.setUserRole(UserRole.USER);

            return new GetUserDTO(userRepository.save(newUser));
        }catch (DuplicateKeyException e){
            throw new UniqueConstraintViolatedException();
        }

    }

    public GetUserDTO getUserByUsername(String username){

        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new NotFoundException("User", username);

        return new GetUserDTO(user.get());

    }

    public GetUserDTO update(UpdateUserDTO updatedUser){

        //Verifying if the sent user id is not the same from the current authenticated user
        if(updatedUser.id() != CurrentUser.getCurrentAuthenticatedUser().getId()) throw new AuthException("An authentication error occurred...");

        Optional<User> user = userRepository.findByUsername(updatedUser.username());
        if(user.isEmpty()) throw new NotFoundException("User", updatedUser.username());

        BeanUtils.copyProperties(updatedUser, user.get());

        return new GetUserDTO(userRepository.save(user.get()));

    }

    @Transactional
    public void delete(String username){

        //Verifying if the sent user username is not the same from the current authenticated user
        if(!username.equals(CurrentUser.getCurrentAuthenticatedUser().getUsername())) throw new AuthException("An authentication error occurred...");

        userRepository.deleteByUsername(username);
        postRepository.deleteByAuthorUsername(username);

    }

}
