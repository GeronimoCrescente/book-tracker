package com.backend.booktrackerbackend.controllers.requests;


import lombok.Data;

@Data
public class ModifyBookRequest {

    private String author;

    private String description;

    private Boolean readState;

}
