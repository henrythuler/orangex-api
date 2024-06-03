package br.com.orangex.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record CreatePostDTO(
        @NotBlank(message = "Your post can't be blank!")
        @Size(min = 1, max = 55, message = "The characters maximum is 55")
        String body,
        @NotNull(message = "Post date is required!")
        @JsonProperty("posted_at")
        Instant postedAt,
        @NotNull(message = "Author is required!")
        AuthorDTO author
) {
}
