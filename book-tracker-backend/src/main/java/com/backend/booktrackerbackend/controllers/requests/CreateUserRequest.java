package com.backend.booktrackerbackend.controllers.requests;


import com.backend.booktrackerbackend.models.Book;
import com.backend.booktrackerbackend.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class CreateUserRequest {
    @JsonProperty(required = true)
    private String username;

    @JsonProperty(required = true)
    private String email;

    @JsonProperty(required = true)
    private String password;

    private PasswordEncoder passwordEncoder;


    public User toEntity() {
        return User.builder()
                .id(null)
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
