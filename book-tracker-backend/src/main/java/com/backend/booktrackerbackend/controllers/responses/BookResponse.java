package com.backend.booktrackerbackend.controllers.responses;

import com.backend.booktrackerbackend.models.Book;
import com.backend.booktrackerbackend.models.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {
    private Integer id;
    private String name;
    private String author;
    private String description;
    private Boolean readState;
    private Category category;

    public static BookResponse from(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .description(book.getDescription())
                .readState(book.getReadState())
                .category(book.getCategory())
                .build();
    }
}
