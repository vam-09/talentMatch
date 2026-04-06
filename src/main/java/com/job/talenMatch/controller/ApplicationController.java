package com.job.talenMatch.controller;

import com.job.talenMatch.dto.ApplicationRequest;
import com.job.talenMatch.dto.JobApplicationResponseDto;
import com.job.talenMatch.model.Application;
import com.job.talenMatch.service.ApplicationService;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService){
        this.applicationService = applicationService;
    }

    @PostMapping("/apply")
    public ResponseEntity<String> submitApplication(@RequestBody ApplicationRequest applicationRequest, Authentication authentication){
        String username = authentication.getName(); // This will be the email/username used for login

        Long jobId = applicationRequest.getJobId();
        
        try {
            applicationService.addApplication(username, jobId);
            return ResponseEntity.ok("Application submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Application failed: " + e.getMessage());
        }
    }

    @GetMapping("/userApplications")
    public List<Application> getJobApplications(Authentication authentication){
        String userName = authentication.getName();
        try{
            return applicationService.getJobApplications(userName);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

    }
}
