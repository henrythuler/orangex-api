package br.com.orangex.api.dto;

import br.com.orangex.api.model.User;

import java.time.LocalDate;

public record GetUserDTO(
        String id,
        String name,
        String email,
        String nickname,
        LocalDate birthDate
) {

    public GetUserDTO(User user){
        this(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getNickname(),
            user.getBirthDate()
        );
    }

}
