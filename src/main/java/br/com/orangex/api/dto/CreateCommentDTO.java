package br.com.orangex.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record CreateCommentDTO(
        @NotBlank(message = "Your comment can't be blank!")
        String body,
        @NotNull(message = "Comment date is required!")
        @JsonProperty("commented_at")
        Instant commentedAt,
        @NotNull(message = "Author is required!")
        AuthorDTO author
) {
}
