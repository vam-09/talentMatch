package com.job.talenMatch.controller;

import com.job.talenMatch.model.User;
import com.job.talenMatch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return "User registered successfully";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Application is running";
    }

}
