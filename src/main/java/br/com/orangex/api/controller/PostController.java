package br.com.orangex.api.controller;

import br.com.orangex.api.dto.CreatePostDTO;
import br.com.orangex.api.model.Post;
import br.com.orangex.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody CreatePostDTO postDTO){
        Post newPost = service.create(postDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId()).toUri();
        return ResponseEntity.created(location).body(newPost);
    }

}
