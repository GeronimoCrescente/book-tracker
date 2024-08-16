package com.backend.booktrackerbackend.controllers.responses;

import com.backend.booktrackerbackend.models.Note;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoteResponse {
    private Integer id;
    private String tittle;
    private String date;
    private String body;

    public static NoteResponse from(Note note) {
        return NoteResponse.builder()
                .id(note.getId())
                .tittle(note.getTittle())
                .date(note.getDate().toString())
                .body(note.getBody())
                .build();
    }
}
