package br.com.orangex.api.dto;

import br.com.orangex.api.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record GetUserDTO(
        String id,
        String name,
        String email,
        String username,
        @JsonProperty("birth_date")
        LocalDate birthDate
) {

    public GetUserDTO(User user){
        this(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getUsername(),
            user.getBirthDate()
        );
    }

}
