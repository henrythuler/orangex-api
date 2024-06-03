package br.com.orangex.api.repository;

import br.com.orangex.api.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

    void deleteByAuthorUsername(String username);

}
