package com.job.talenMatch.controller;

import com.job.talenMatch.dto.JobApplicationResponseDto;
import com.job.talenMatch.dto.JobRequestDto;
import com.job.talenMatch.model.Job;
import com.job.talenMatch.model.User;
import com.job.talenMatch.service.JobService;
import com.job.talenMatch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    @ResponseBody
    public List<Job> getJobs(){
        return jobService.findAllJobs();
    }

    @GetMapping("/allJobs")
    public String getAllJobs(){
        return "forward:/job-list.html";
    }

    @GetMapping("/searchJob")
    @ResponseBody
    public List<Job> searchJobs(@RequestParam("keyword") String keyword){
        return jobService.searchJobs(keyword);
    }

    @GetMapping("/userJobs")
    @ResponseBody
    public List<Job> userJobs(Authentication authentication){
        String userName = authentication.getName();

        return jobService.findUserJobs(userName);
    }

    @PostMapping("job/create")
    @ResponseBody
    public Job addJob(@RequestBody JobRequestDto jobRequestDto, Authentication authentication){
        String userName = authentication.getName();
        return jobService.addJob(jobRequestDto, userName);
    }

    @PostMapping("/job/update")
    @ResponseBody
    public Job updateJob(@RequestBody Job job){

        return jobService.updateJob(job);
    }

    @DeleteMapping("/job/delete")
    @ResponseBody
    public void deleteJob(@RequestParam("id") Long jobId){
        jobService.deleteJob(jobId);
    }

}
