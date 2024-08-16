package com.backend.booktrackerbackend.controllers;

import com.backend.booktrackerbackend.controllers.requests.CreateUserRequest;
import com.backend.booktrackerbackend.controllers.responses.UserResponse;
import com.backend.booktrackerbackend.models.Category;
import com.backend.booktrackerbackend.models.User;
import com.backend.booktrackerbackend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        try {
            var users = userServices.findAll().stream()
                    .map(UserResponse::from)
                    .toList();

            return users.isEmpty() ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok(users);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer userId) {
        try {
            Optional<User> user = userServices.findById(userId);

            return user.map(UserResponse::from)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.noContent().build());

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<User> createNewUser(@RequestBody CreateUserRequest newUser) {
        try {
            User created = userServices.saveUser(newUser.toEntity());
            URI ubi = URI.create(String.format("/users/%d", created.getId()));

            return ResponseEntity.created(ubi).body(created);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{deleteId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer deleteId) {
        try {
            userServices.deleteUserById(deleteId);

            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
