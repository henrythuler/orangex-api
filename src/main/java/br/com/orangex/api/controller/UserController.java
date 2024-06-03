package br.com.orangex.api.controller;

import br.com.orangex.api.dto.GetUserDTO;
import br.com.orangex.api.dto.UpdateUserDTO;
import br.com.orangex.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/{username}")
    public ResponseEntity<GetUserDTO> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(service.getUserByUsername(username));
    }

    @PutMapping(value = "/users/update")
    public ResponseEntity<GetUserDTO> update(@RequestBody @Valid UpdateUserDTO userDTO){
        return ResponseEntity.ok(service.update(userDTO));
    }

    @DeleteMapping(value = "/users/del")
    public ResponseEntity<Void> delete(@RequestBody String username){
        service.delete(username);
        return ResponseEntity.noContent().build();
    }

}
