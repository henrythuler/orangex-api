package br.com.orangex.api.service;

import br.com.orangex.api.dto.GetUserDTO;
import br.com.orangex.api.dto.PostUserDTO;
import br.com.orangex.api.model.User;
import br.com.orangex.api.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public GetUserDTO create(PostUserDTO user){

        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        String passwordEncoded = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(passwordEncoded);

        return new GetUserDTO(repository.save(newUser));

    }

}
