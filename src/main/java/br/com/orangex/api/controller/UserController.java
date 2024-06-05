package br.com.orangex.api.controller;

import br.com.orangex.api.dto.DeleteUserDTO;
import br.com.orangex.api.dto.GetUserDTO;
import br.com.orangex.api.dto.UpdateUserDTO;
import br.com.orangex.api.exception.StandardException;
import br.com.orangex.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Operation(summary = "Users info",
            description = "Gets all the user's info and posts by username",
            tags = {"Users"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GetUserDTO.class)
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
    @GetMapping(value = "/{username}", produces = "application/json")
    public ResponseEntity<GetUserDTO> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(service.getUserByUsername(username));
    }

    @Operation(summary = "Updates user's info",
            description = "Updates the user's info by passing an User JSON representation",
            tags = {"Users"},
            responses = {
                @ApiResponse(description = "Updated", responseCode = "200", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GetUserDTO.class)
                )),
                @ApiResponse(description = "Bad Request", responseCode = "400",  content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Not Found", responseCode = "404", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Forbidden", responseCode = "403", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                ))
            })
    @PutMapping(value = "/users/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GetUserDTO> update(@RequestBody(description = "Updated user", required = true, content = @Content(schema = @Schema(implementation = UpdateUserDTO.class))) @Valid UpdateUserDTO userDTO){
        return ResponseEntity.ok(service.update(userDTO));
    }

    @Operation(summary = "Deletes a user",
            description = "Deletes a user and all of he's posts by passing its username",
            tags = {"Users"},
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
    @DeleteMapping(value = "/users/del", consumes = "application/json")
    public ResponseEntity<Void> delete(@RequestBody(description = "User's to be deleted username", required = true, content = @Content(schema = @Schema(implementation = DeleteUserDTO.class))) DeleteUserDTO deleteUserDTO){
        service.delete(deleteUserDTO.username());
        return ResponseEntity.noContent().build();
    }

}
