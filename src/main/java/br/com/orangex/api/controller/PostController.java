package br.com.orangex.api.controller;

import br.com.orangex.api.dto.CreatePostDTO;
import br.com.orangex.api.model.Post;
import br.com.orangex.api.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping(value = "/posts")
    public ResponseEntity<Post> create(@RequestBody @Valid CreatePostDTO postDTO){
        Post newPost = service.create(postDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId()).toUri();
        return ResponseEntity.created(location).body(newPost);
    }

}
