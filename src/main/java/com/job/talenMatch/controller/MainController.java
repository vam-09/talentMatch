package com.job.talenMatch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String home() {
        return "Welcome to TalentMatch API";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Application is running";
    }

}
