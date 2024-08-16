package com.backend.booktrackerbackend.controllers.requests;

import com.backend.booktrackerbackend.models.Book;
import com.backend.booktrackerbackend.models.Category;
import com.backend.booktrackerbackend.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateBookRequest {
    @JsonProperty(required = true)
    private String name;

    private String author;

    private String description;

    @JsonProperty(required = true)
    private Boolean readState;

    private Category category;

    @JsonProperty(required = true)
    private User user;

    public Book toEntity() {
        return Book.builder()
                .id(null)
                .name(name)
                .author(author)
                .description(description)
                .readState(readState)
                .category(category)
                .user(user)
                .build();
    }
}
