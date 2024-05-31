package br.com.orangex.api.repository;

import br.com.orangex.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends MongoRepository<User, String> {

    //Finds user for authentication
    UserDetails findByEmail(String email);

}
