package br.com.orangex.api.controller;

import br.com.orangex.api.dto.GetUserDTO;
import br.com.orangex.api.dto.LoginDTO;
import br.com.orangex.api.dto.PostUserDTO;
import br.com.orangex.api.model.User;
import br.com.orangex.api.repository.UserRepository;
import br.com.orangex.api.security.TokenService;
import br.com.orangex.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginDTO loginDTO){

        UsernamePasswordAuthenticationToken userAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
        Authentication auth = authenticationManager.authenticate(userAuthenticationToken);

        User user = (User) auth.getPrincipal();

        String token = tokenService.generateToken(user);

        Map<String, String> returnedToken = new HashMap<>();
        returnedToken.put("token", token);

        return ResponseEntity.ok(returnedToken);

    }

    @PostMapping("/register")
    public ResponseEntity<GetUserDTO> register(@RequestBody @Valid PostUserDTO postUserDTO){

        GetUserDTO getUserDTO = userService.create(postUserDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(getUserDTO.id()).toUri();
        return ResponseEntity.created(location).body(getUserDTO);

    }

}
