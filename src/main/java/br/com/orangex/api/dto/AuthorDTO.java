package br.com.orangex.api.dto;

import br.com.orangex.api.model.User;

public record AuthorDTO(
        String username,
        String name
) {

    public AuthorDTO(User user){
        this(
            user.getUsername(),
            user.getName()
        );
    }

}
