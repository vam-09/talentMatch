package com.job.talenMatch.service;

import com.job.talenMatch.model.Job;
import com.job.talenMatch.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepo jobRepo;

    @Autowired
    JobService(JobRepo jobRepo){
        this.jobRepo = jobRepo;
    }

    public Job findJob(Long jobId) {
        return jobRepo.findJobByJobId(jobId);
    }

    public List<Job> findJobs() {
        return jobRepo.findAll();
    }
}
