package br.com.orangex.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record CreatePostDTO(
        String body,
        @JsonProperty("posted_at")
        Instant postedAt,
        AuthorDTO author
) {
}
