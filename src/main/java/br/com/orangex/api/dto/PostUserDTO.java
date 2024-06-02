package br.com.orangex.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PostUserDTO(
        @NotBlank(message = "Name is required!")
        @Size(min = 2, max = 80, message = "Name is between 2 and 80 characters")
        String name,
        @NotBlank(message = "Email is required!")
        @Email(message = "Invalid email!")
        String email,
        @NotBlank(message = "Username is required!")
        @Size(min = 2, max = 30, message = "Username is between 2 and 30 characters")
        String username,
        @NotBlank(message = "Password is required!")
        @Size(min = 2, max = 20, message = "Password is between 8 and 20 characters")
        String password,
        @JsonProperty("birth_date")
        @NotNull(message = "Birth date is required!")
        LocalDate birthDate
) {
}
