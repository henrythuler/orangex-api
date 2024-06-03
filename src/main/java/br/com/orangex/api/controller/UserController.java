package br.com.orangex.api.controller;

import br.com.orangex.api.dto.GetUserDTO;
import br.com.orangex.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/{username}")
    public ResponseEntity<GetUserDTO> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(service.getUserByUsername(username));
    }

}
