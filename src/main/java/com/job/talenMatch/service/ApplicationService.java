package com.job.talenMatch.service;

import com.job.talenMatch.dto.JobApplicationResponseDto;
import com.job.talenMatch.model.*;
import com.job.talenMatch.repo.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.job.talenMatch.model.UserRole.APPLICANT;

@Service
public class ApplicationService {

    private final ApplicationRepo applicationRepo;
    private final UserService userService;
    private final JobService jobService;

    @Autowired
    ApplicationService(ApplicationRepo applicationRepo, UserService userService, JobService jobService){
        this.applicationRepo = applicationRepo;
        this.userService = userService;
        this.jobService = jobService;
    }

    public void addApplication(String userName, Long jobId){
        Application application = new Application();

        User user = userService.findUser(userName);
        Job job = jobService.findJob(jobId);
        application.setJob(job);
        application.setApplicant(user);
        application.setApplicationDate(ZonedDateTime.now());
        application.setUpdatedAt(ZonedDateTime.now());
        application.setStatus(ApplicationStatus.PENDING);

        applicationRepo.save(application);

    }

    public List<Application> getJobApplications(String userName) {
        User user = userService.findUser(userName);
        List<Application> userApplications = applicationRepo.findApplicationByApplicant(user);

        return userApplications;
    }

    public List<Application> getJobSpecificApplications(Long jobId) {
        Job job = jobService.findJob(jobId);
        return applicationRepo.findApplicationByJob(job);
    }

}
