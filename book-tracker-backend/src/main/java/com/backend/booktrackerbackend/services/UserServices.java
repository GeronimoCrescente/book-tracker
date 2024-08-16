package com.backend.booktrackerbackend.services;

import com.backend.booktrackerbackend.models.User;
import com.backend.booktrackerbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }

    public User saveUser(User newUser) {
        return userRepository.save(newUser);
    }

    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }

}
