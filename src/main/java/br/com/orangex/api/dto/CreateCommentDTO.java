package br.com.orangex.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record CreateCommentDTO(
        @NotBlank(message = "Your comment can't be blank!")
        @Size(min = 1, max = 55, message = "The characters maximum is 55")
        String body,
        @NotNull(message = "Comment date is required!")
        @JsonProperty("commented_at")
        Instant commentedAt,
        @NotNull(message = "Author is required!")
        AuthorDTO author
) {
}
