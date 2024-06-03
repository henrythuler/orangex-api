package br.com.orangex.api.dto;

import br.com.orangex.api.model.Post;
import br.com.orangex.api.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public record GetUserDTO(
        String id,
        String name,
        String email,
        String username,
        @JsonProperty("birth_date")
        LocalDate birthDate,
        List<Post> posts
) {

    public GetUserDTO(User user){
        this(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getUsername(),
            user.getBirthDate(),
            user.getPosts()
        );
    }

}
