package br.com.orangex.api.controller;

import br.com.orangex.api.dto.CreateCommentDTO;
import br.com.orangex.api.dto.CreatePostDTO;
import br.com.orangex.api.dto.UpdateCommentDTO;
import br.com.orangex.api.dto.UpdatePostDTO;
import br.com.orangex.api.exception.StandardException;
import br.com.orangex.api.model.Post;
import br.com.orangex.api.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class PostController {

    @Autowired
    private PostService service;

    @Operation(summary = "Creates a post",
            description = "Creates a new post by passing a post JSON representation",
            tags = {"Posts"},
            responses = {
                @ApiResponse(description = "Created", responseCode = "201", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class)
                )),
                @ApiResponse(description = "Bad Request", responseCode = "400",  content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Forbidden", responseCode = "403", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                ))
            })
    @PostMapping(value = "/posts", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Post> create(@RequestBody @Valid CreatePostDTO postDTO){
        Post newPost = service.create(postDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId()).toUri();
        return ResponseEntity.created(location).body(newPost);
    }

    @Operation(summary = "Gets all posts",
            description = "Gets all created posts",
            tags = {"Posts"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)
                )),
                @ApiResponse(description = "Bad Request", responseCode = "400",  content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Forbidden", responseCode = "403", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                ))
            })
    @GetMapping(value = "/feed", produces = "application/json")
    public ResponseEntity<Page<Post>> getAll(Pageable pageable){
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @Operation(summary = "Gets a post by id",
            description = "Gets a post by the passed id",
            tags = {"Posts"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)
                )),
                @ApiResponse(description = "Bad Request", responseCode = "400",  content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Forbidden", responseCode = "403", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Not Found", responseCode = "404", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                ))
            })
    @GetMapping(value = "/posts/{id}", produces = "application/json")
    public ResponseEntity<Post> getById(@PathVariable String id){
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Updates a post",
            description = "Updates a post by passing a new post JSON representation",
            tags = {"Posts"},
            responses = {
                @ApiResponse(description = "Updated", responseCode = "200", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)
                )),
                @ApiResponse(description = "Bad Request", responseCode = "400",  content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Forbidden", responseCode = "403", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Not Found", responseCode = "404", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                ))
            })
    @PutMapping(value = "/posts", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Post> update(@RequestBody @Valid UpdatePostDTO postDTO){
        return ResponseEntity.ok(service.update(postDTO));
    }

    @Operation(summary = "Likes a post",
            description = "Likes a post by the passed id",
            tags = {"Posts"},
            responses = {
                @ApiResponse(description = "Updated", responseCode = "200", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)
                )),
                @ApiResponse(description = "Bad Request", responseCode = "400",  content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Forbidden", responseCode = "403", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Not Found", responseCode = "404", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                ))
            })
    @PutMapping(value = "/posts/{id}/like", produces = "application/json")
    public ResponseEntity<Integer> like(@PathVariable String id){
        return ResponseEntity.ok(service.like(id));
    }

    @Operation(summary = "Creates a comment",
            description = "Creates a new comment by passing a comment JSON representation and a post id",
            tags = {"Posts"},
            responses = {
                @ApiResponse(description = "Updated", responseCode = "200", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)
                )),
                @ApiResponse(description = "Bad Request", responseCode = "400",  content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Forbidden", responseCode = "403", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Not Found", responseCode = "404", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                ))
            })
    @PutMapping(value = "/posts/{id}/comment", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Post> comment(@PathVariable String id, @RequestBody @Valid CreateCommentDTO commentDTO){
        return ResponseEntity.ok(service.comment(id, commentDTO));
    }

    @Operation(summary = "Updates a comment",
            description = "Updates a comment by passing a new comment JSON representation and a post id",
            tags = {"Posts"},
            responses = {
                @ApiResponse(description = "Updated", responseCode = "200", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)
                )),
                @ApiResponse(description = "Bad Request", responseCode = "400",  content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Forbidden", responseCode = "403", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Not Found", responseCode = "404", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                ))
            })
    @PutMapping(value = "/posts/{postId}/comment/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Post> updateComment(@PathVariable String postId, @RequestBody @Valid UpdateCommentDTO commentDTO){
        return ResponseEntity.ok(service.updateComment(postId, commentDTO));
    }

    @Operation(summary = "Deletes a post",
            description = "Deletes a post by passing its id",
            tags = {"Posts"},
            responses = {
                @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400",  content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Forbidden", responseCode = "403", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Not Found", responseCode = "404", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                ))
            })
    @DeleteMapping(value = "/posts/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes a comment",
            description = "Deletes a comment by passing its id and the post id",
            tags = {"Posts"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400",  content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                    )),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                    )),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                    )),
                    @ApiResponse(description = "Not Found", responseCode = "404", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                    )),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                    ))
            })
    @DeleteMapping(value = "/posts/{postId}/comment/del/{commentId}")
    public ResponseEntity<Post> deleteComment(@PathVariable String postId, @PathVariable String commentId){
        return ResponseEntity.ok(service.deleteComment(postId, commentId));
    }

}
