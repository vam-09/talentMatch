package com.job.talenMatch.controller;

import com.job.talenMatch.model.Job;
import com.job.talenMatch.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class JobController {

    private final JobService jobService;

    @Autowired
    JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    @ResponseBody
    public List<Job> getJobs(){
        return jobService.findJobs();
    }

    @GetMapping("/allJobs")
    public String getAllJobs(){
        return "forward:/job-list.html";
    }

}
