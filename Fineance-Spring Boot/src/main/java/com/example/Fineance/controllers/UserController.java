package com.example.Fineance.controllers;

import com.example.Fineance.models.User;
import com.example.Fineance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id_user}")
    public User getUserById(@PathVariable Long id_user) {
        return userService.getUserById(id_user);
    }
}
