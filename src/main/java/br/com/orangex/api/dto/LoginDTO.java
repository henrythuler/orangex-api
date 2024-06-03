package br.com.orangex.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @Email(message = "Invalid email!")
        @NotBlank(message = "Email is required!")
        String email,
        @NotBlank(message = "Password is required!")
        String password
) {
}
