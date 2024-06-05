package br.com.orangex.api.controller;

import br.com.orangex.api.dto.CreateUserDTO;
import br.com.orangex.api.dto.GetUserDTO;
import br.com.orangex.api.dto.LoginDTO;
import br.com.orangex.api.dto.TokenDTO;
import br.com.orangex.api.exception.StandardException;
import br.com.orangex.api.model.User;
import br.com.orangex.api.security.TokenService;
import br.com.orangex.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(summary = "Login",
            description = "Authenticates an user by passing its credentials on a JSON representation",
            tags = {"Authentication"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TokenDTO.class)
                )),
                @ApiResponse(description = "Bad Request", responseCode = "400",  content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                ))
            })
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginDTO){

        UsernamePasswordAuthenticationToken userAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
        Authentication auth = authenticationManager.authenticate(userAuthenticationToken);

        User user = (User) auth.getPrincipal();

        String token = tokenService.generateToken(user);

        return ResponseEntity.ok(new TokenDTO(token));

    }

    @Operation(summary = "Register",
            description = "Registers an user by passing its credentials on a JSON representation",
            tags = {"Authentication"},
            responses = {
                @ApiResponse(description = "Created", responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GetUserDTO.class))
                }),
                @ApiResponse(description = "Bad Request", responseCode = "400",  content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Conflict", responseCode = "409", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                )),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StandardException.class)
                ))
            })
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GetUserDTO> register(@RequestBody @Valid CreateUserDTO createUserDTO){

        GetUserDTO getUserDTO = userService.create(createUserDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(getUserDTO.id()).toUri();
        return ResponseEntity.created(location).body(getUserDTO);

    }

}
