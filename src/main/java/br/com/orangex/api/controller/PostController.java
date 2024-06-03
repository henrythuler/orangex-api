package br.com.orangex.api.controller;

import br.com.orangex.api.dto.CreatePostDTO;
import br.com.orangex.api.dto.UpdatePostDTO;
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
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody @Valid CreatePostDTO postDTO){
        Post newPost = service.create(postDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId()).toUri();
        return ResponseEntity.created(location).body(newPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getById(@PathVariable String id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping
    public ResponseEntity<Post> update(@RequestBody @Valid UpdatePostDTO postDTO){
        return ResponseEntity.ok(service.update(postDTO));
    }

    @DeleteMapping(value = "/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
