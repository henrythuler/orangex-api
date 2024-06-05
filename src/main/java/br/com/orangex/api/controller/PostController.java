package br.com.orangex.api.controller;

import br.com.orangex.api.dto.CreateCommentDTO;
import br.com.orangex.api.dto.CreatePostDTO;
import br.com.orangex.api.dto.UpdateCommentDTO;
import br.com.orangex.api.dto.UpdatePostDTO;
import br.com.orangex.api.model.Post;
import br.com.orangex.api.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody @Valid CreatePostDTO postDTO){
        Post newPost = service.create(postDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId()).toUri();
        return ResponseEntity.created(location).body(newPost);
    }

    @GetMapping("/feed")
    public ResponseEntity<Page<Post>> getAll(Pageable pageable){
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getById(@PathVariable String id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/posts")
    public ResponseEntity<Post> update(@RequestBody @Valid UpdatePostDTO postDTO){
        return ResponseEntity.ok(service.update(postDTO));
    }

    @PutMapping("/posts/{id}/like")
    public ResponseEntity<Integer> like(@PathVariable String id){
        return ResponseEntity.ok(service.like(id));
    }

    @PutMapping("/posts/{id}/comment")
    public ResponseEntity<Post> comment(@PathVariable String id, @RequestBody @Valid CreateCommentDTO commentDTO){
        return ResponseEntity.ok(service.comment(id, commentDTO));
    }

    @PutMapping("/posts/{postId}/comment/update")
    public ResponseEntity<Post> updateComment(@PathVariable String postId, @RequestBody @Valid UpdateCommentDTO commentDTO){
        return ResponseEntity.ok(service.updateComment(postId, commentDTO));
    }

    @DeleteMapping("/posts/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/posts/{postId}/comment/del/{commentId}")
    public ResponseEntity<Post> deleteComment(@PathVariable String postId, @PathVariable String commentId){
        return ResponseEntity.ok(service.deleteComment(postId, commentId));
    }

}
