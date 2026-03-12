package com.job.talenMatch.controller;

import com.job.talenMatch.model.User;
import com.job.talenMatch.model.UserRole;
import com.job.talenMatch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String home() {
        User user = User.builder().userName("vam").email("vam@gmail.com").role(UserRole.APPLICANT).build();
        userService.registerUser(user);
        return "Welcome to TalentMatch API";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Application is running";
    }

}
