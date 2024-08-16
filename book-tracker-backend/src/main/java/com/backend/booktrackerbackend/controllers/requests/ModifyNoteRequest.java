package com.backend.booktrackerbackend.controllers.requests;


import lombok.Data;

@Data
public class ModifyNoteRequest {
    private String tittle;

    private String body;
}
