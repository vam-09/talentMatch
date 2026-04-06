package com.job.talenMatch.controller;

import com.job.talenMatch.dto.RegistrationRequestDto;
import com.job.talenMatch.model.User;
import com.job.talenMatch.service.SkillService;
import com.job.talenMatch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//user shd be able to filter the jobs based on diff criterias - ✅
//live streaming. - instant search - ✅
//if user is recruiter, they shd be able to see the jobs they have added - ✅
//shd be able to search job based on name and skills - ✅
//Applicant shd be able to see which jobs they have applied to in job-list ✅
// add the job, update the job, delete the job
// update user details
// Recruiter shd see the applicant for each job


@Controller
@RequestMapping("/api")
public class MainController {

    private final UserService userService;
    private final SkillService skillService;

    @Autowired
    public MainController(UserService userService, SkillService skillService) {
        this.userService = userService;
        this.skillService = skillService;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequestDto registrationRequestDto) {
        User userRegistered = userService.registerUser(registrationRequestDto.getUser());
        skillService.addUserSkills(registrationRequestDto.getSkills(), userRegistered);
        if(userRegistered != null) {
            return ResponseEntity.status(HttpStatus.OK).body("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed");
        }
    }

    @GetMapping("/register")
    public String register(){
        return "forward:/registration.html";
    }

    @GetMapping("/user/me")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("username", authentication.getName());
        // Add user role to the response
        authentication.getAuthorities().stream()
                .filter(a -> a.getAuthority().startsWith("ROLE_"))
                .findFirst()
                .ifPresent(a -> userInfo.put("role", a.getAuthority().substring(5))); // Remove "ROLE_" prefix
        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/health")
    @ResponseBody
    public String healthCheck() {
        return "Application is running";
    }

}
