package br.com.orangex.api.model;


import br.com.orangex.api.dto.AuthorDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Comment {

    private String id;
    private String body;
    @Field("commented_at")
    private Instant commentedAt;
    private AuthorDTO author;

    public Comment() {
        this.id = UUID.randomUUID().toString();
    }

    public Comment(String body, Instant commentedAt, AuthorDTO author) {
        this.id = UUID.randomUUID().toString();;
        this.body = body;
        this.commentedAt = commentedAt;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Instant getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(Instant commentedAt) {
        this.commentedAt = commentedAt;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
