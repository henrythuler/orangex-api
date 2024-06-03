package br.com.orangex.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public record CreatePostDTO(
        @Size(min = 1, max = 55, message = "The characters maximum is 55")
        String body,
        @JsonProperty("posted_at")
        Instant postedAt,
        AuthorDTO author
) {
}
