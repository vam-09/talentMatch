package com.job.talenMatch.controller;

import com.job.talenMatch.model.Skill;
import com.job.talenMatch.model.User;
import com.job.talenMatch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User getUser(@RequestParam("userName") String userName) {
        return userService.findUser(userName);
    }

    @PostMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

//    @GetMapping("/machingProfiles")
//    public List<User> matchingProfiles(@RequestBody List<Skill> skills){
//
//    }
}
