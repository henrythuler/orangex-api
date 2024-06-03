package br.com.orangex.api.repository;

import br.com.orangex.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {

    //Finds user for login authentication
    UserDetails findByEmail(String email);

    //Finds user for verify token authentication
    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);

}
