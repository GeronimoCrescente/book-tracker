package com.backend.booktrackerbackend.controllers.responses;


import com.backend.booktrackerbackend.models.Book;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponseSimplified {
    private Integer id;
    private String name;
    private Boolean readState;

    public static BookResponseSimplified from(Book book) {
        return BookResponseSimplified.builder()
                .id(book.getId())
                .name(book.getName())
                .readState(book.getReadState())
                .build();
    }
}
