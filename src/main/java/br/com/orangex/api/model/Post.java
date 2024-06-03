package br.com.orangex.api.model;

import br.com.orangex.api.dto.AuthorDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document
public class Post {

    @Id
    private String id;
    private String body;
    @JsonProperty("posted_at")
    @Field("posted_at")
    private Instant postedAt;

    private List<String> likes = new ArrayList<>();

    private AuthorDTO author;

    private List<Comment> comments = new ArrayList<>();

    public Post() {
    }

    public Post(String id, String body, Instant postedAt, AuthorDTO author) {
        this.id = id;
        this.body = body;
        this.postedAt = postedAt;
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

    public Instant getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Instant postedAt) {
        this.postedAt = postedAt;
    }

    public List<String> getLikes() {
        return likes;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
