package com.backend.booktrackerbackend.controllers.requests;

import com.backend.booktrackerbackend.models.Book;
import com.backend.booktrackerbackend.models.Note;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class CreateNoteRequest {
    private String tittle;

    private String body;

    @JsonProperty(required = true)
    private Book book;

    public Note toEntity() {
        return Note.builder()
                .id(null)
                .tittle(tittle)
                .date(Date.from(Instant.now()))
                .body(body)
                .book(book)
                .build();
    }
}
