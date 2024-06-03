package br.com.orangex.api.service;

import br.com.orangex.api.dto.GetUserDTO;
import br.com.orangex.api.dto.CreateUserDTO;
import br.com.orangex.api.exception.NotFoundException;
import br.com.orangex.api.exception.UniqueConstraintViolatedException;
import br.com.orangex.api.model.User;
import br.com.orangex.api.model.UserRole;
import br.com.orangex.api.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public GetUserDTO create(CreateUserDTO user){

        try{
            User newUser = new User();
            BeanUtils.copyProperties(user, newUser);
            String passwordEncoded = passwordEncoder.encode(newUser.getPassword());
            newUser.setPassword(passwordEncoded);
            newUser.setUserRole(UserRole.USER);

            return new GetUserDTO(repository.save(newUser));
        }catch (DuplicateKeyException e){
            throw new UniqueConstraintViolatedException();
        }

    }

    public GetUserDTO findById(String id){

        return new GetUserDTO(repository.findById(id).orElseThrow(() -> new NotFoundException("User", id)));

    }

    public GetUserDTO getUserByUsername(String username){

        Optional<User> user = repository.findByUsername(username);
        if(user.isEmpty()) throw new NotFoundException("User", username);

        return new GetUserDTO(user.get());

    }

}
