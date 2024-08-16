package com.backend.booktrackerbackend.controllers.requests;

import com.backend.booktrackerbackend.models.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateCategoryRequest {
    @JsonProperty(required = true)
    private String name;

    public Category toEntity() {
        return Category.builder()
                .id(null)
                .name(name)
                .build();
    }
}
