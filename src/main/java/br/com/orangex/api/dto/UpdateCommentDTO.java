package br.com.orangex.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCommentDTO(
        @NotBlank(message = "ID is required!")
        String id,
        @NotBlank(message = "Your post can't be blank!")
        @Size(min = 1, max = 55, message = "The characters maximum is 55")
        String body,
        AuthorDTO author
) {
}
