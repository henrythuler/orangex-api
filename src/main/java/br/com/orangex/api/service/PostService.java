package br.com.orangex.api.service;

import br.com.orangex.api.dto.CreateCommentDTO;
import br.com.orangex.api.dto.CreatePostDTO;
import br.com.orangex.api.dto.UpdatePostDTO;
import br.com.orangex.api.exception.AuthException;
import br.com.orangex.api.exception.NotFoundException;
import br.com.orangex.api.model.Comment;
import br.com.orangex.api.model.Post;
import br.com.orangex.api.model.User;
import br.com.orangex.api.repository.CommentRepository;
import br.com.orangex.api.repository.PostRepository;
import br.com.orangex.api.repository.UserRepository;
import br.com.orangex.api.util.CurrentUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private CommentRepository commentRepository;

    public Post create(CreatePostDTO postDTO){

        Post newPost = new Post();
        BeanUtils.copyProperties(postDTO, newPost);
        User currentUser = CurrentUser.getCurrentAuthenticatedUser();

        //Verifying if the post author username and name are the same from the current authenticated user
        if(newPost.getAuthor().username().equals(currentUser.getUsername()) && newPost.getAuthor().name().equals(currentUser.getName())){
            User user = userRepository.findById(currentUser.getId()).get();

            newPost = postRepository.save(newPost);

            user.getPosts().add(newPost);
            userRepository.save(user);

            return newPost;
        }else{
            throw new AuthException("An authentication error occurred...");
        }

    }

    public Page<Post> getAll(Pageable pageable){
        return postRepository.findAll(pageable);
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

        if(!post.get().getAuthor().username().equals(CurrentUser.getCurrentAuthenticatedUser().getUsername()) || !post.get().getAuthor().name().equals(CurrentUser.getCurrentAuthenticatedUser().getName())) throw new AuthException("An authentication exception ocurred...");

        postRepository.deleteById(id);

    }

    public Integer like(String id){

        Post post = getById(id);

        boolean alreadyLiked = post.getLikes().contains(CurrentUser.getCurrentAuthenticatedUser().getUsername());

        if(alreadyLiked){
            post.getLikes().remove(CurrentUser.getCurrentAuthenticatedUser().getUsername());
        }else{
            post.getLikes().add(CurrentUser.getCurrentAuthenticatedUser().getUsername());
        }

        postRepository.save(post);

        return post.getLikes().size();

    }

    public Post comment(String id, CreateCommentDTO commentDTO){

        Post post = getById(id);

        if(!commentDTO.author().username().equals(CurrentUser.getCurrentAuthenticatedUser().getUsername()) || !commentDTO.author().name().equals(CurrentUser.getCurrentAuthenticatedUser().getName())) throw new AuthException("An authentication error occured...");

        Comment comment = new Comment();

        BeanUtils.copyProperties(commentDTO, comment);

        comment = commentRepository.save(comment);

        post.getComments().add(comment);
        postRepository.save(post);

        return post;

    }

    public Post deleteComment(String postId, String commentId){

        Optional<Comment> comment = commentRepository.findById(commentId);

        if(comment.isEmpty()) throw new NotFoundException("Comment", commentId);

        Comment foundComment = comment.get();

        if(!foundComment.getAuthor().username().equals(CurrentUser.getCurrentAuthenticatedUser().getUsername())) throw new AuthException("An authentication error occurred...");

        Optional<Post> post = postRepository.findById(postId);

        if(post.isEmpty()) throw new NotFoundException("Post", postId);

        List<Comment> postComments = post.get().getComments();

        Comment deleteComment = null;

        for(Comment c : postComments){
            if(c.getId().equals(commentId)) deleteComment = c;
        }

        postComments.remove(deleteComment);

        commentRepository.deleteById(commentId);

        return postRepository.save(post.get());

    }

}
