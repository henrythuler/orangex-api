package br.com.orangex.api.service;

import br.com.orangex.api.dto.CreatePostDTO;
import br.com.orangex.api.dto.UpdatePostDTO;
import br.com.orangex.api.exception.AuthException;
import br.com.orangex.api.exception.NotFoundException;
import br.com.orangex.api.model.Post;
import br.com.orangex.api.model.User;
import br.com.orangex.api.repository.PostRepository;
import br.com.orangex.api.repository.UserRepository;
import br.com.orangex.api.util.CurrentUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post create(CreatePostDTO postDTO){

        Post newPost = new Post();
        BeanUtils.copyProperties(postDTO, newPost);
        User currentUser = CurrentUser.getCurrentAuthenticatedUser();

        //Verifying if the post author username and name are the same from the current authenticated user
        if(newPost.getAuthor().username().equals(currentUser.getUsername()) && newPost.getAuthor().name().equals(currentUser.getName())){
            User user = userRepository.findById(currentUser.getId()).get();

            newPost.setLikes(0);
            newPost = postRepository.save(newPost);

            user.getPosts().add(newPost);
            userRepository.save(user);

            return newPost;
        }else{
            throw new AuthException("An authentication error occurred...");
        }

    }

    public Post getById(String id){

        Optional<Post> post = postRepository.findById(id);

        if(post.isEmpty()) throw new NotFoundException("Post", id);

        return post.get();

    }

    public Post update(UpdatePostDTO updatedPost){

        Optional<Post> post = postRepository.findById(updatedPost.id());

        if(post.isEmpty()) throw new NotFoundException("Post", updatedPost.id());

        if(!updatedPost.author().username().equals(CurrentUser.getCurrentAuthenticatedUser().getUsername())) throw new AuthException("An authentication error occurred...");

        BeanUtils.copyProperties(updatedPost, post.get());

        return postRepository.save(post.get());

    }

    public void delete(String id){

        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()) throw new NotFoundException("Post", id);

        if(!post.get().getAuthor().username().equals(CurrentUser.getCurrentAuthenticatedUser().getUsername())) throw new AuthException("An authentication exception ocurred...");

        postRepository.deleteById(id);

    }

}
