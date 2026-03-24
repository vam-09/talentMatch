package com.job.talenMatch.controller;

import com.job.talenMatch.dto.JobRequest;
import com.job.talenMatch.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping("/addJob")
    public String createNewJob(@RequestBody JobRequest newJobRequest) {
        jobService.createJob(newJobRequest);
        return "Job created successfully";
    }
    private final String JOB_REQUEST_JSON = """
            {
              "userId": 101,
              "companyName": "Google",
              "jobTitle": "Backend Developer",
              "jobDescription": "Spring Boot Developer",
              "locationCity": "Pune",
              "workModel": "HYBRID",
              "ctc": 1200000,
              "shift": "MORNING_SHIFT",
              "workDays": "Mon-Fri",
              "educationRequirement": "B.Tech",
              "experience": "3-5 Years",
              "jobStatus": "ACTIVE",
              "applicationDeadline": "2026-04-30",
              "skills": [
                {
                  "skillId": 1,
                  "proficiencyLevel": "BASIC"
                },
                {
                  "skillId": 2,
                  "proficiencyLevel": "MODERATE"
                }
              ]
            }
            """;
}
