package com.job.talenMatch.service;

import com.job.talenMatch.dto.JobApplicationResponseDto;
import com.job.talenMatch.dto.JobRequestDto;
import com.job.talenMatch.model.*;
import com.job.talenMatch.repo.ApplicationRepo;
import com.job.talenMatch.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    private final JobRepo jobRepo;
    private final UserService userService;
    private final ApplicationRepo applicationRepo;

    @Autowired
    JobService(JobRepo jobRepo, UserService userService, ApplicationRepo applicationRepo){
        this.jobRepo = jobRepo;
        this.userService = userService;
        this.applicationRepo = applicationRepo;
    }

    public Job findJob(Long jobId) {
        return jobRepo.findJobByJobId(jobId);
    }

    public List<Job> findAllJobs() {
        return jobRepo.findAll();
    }

    public List<Job> searchJobs(String keyword) {
        return jobRepo.findByKeyword(keyword);
    }

    public List<Job> findUserJobs(String userName) {
        User user = userService.findUser(userName);

        return jobRepo.findJobByRecruiter(user);
    }
//    public List<JobApplicationResponseDto> findUserJobs(String userName) {
//        User user = userService.findUser(userName);
//        List<JobApplicationResponseDto> userJobs = new ArrayList<>();
//        if(user.getRole() == UserRole.APPLICANT){
//            userJobs = getJobApplications(user.getUserName());
//        }
//        else{
//            List<Job> jobs = jobRepo.findJobByRecruiter(user);
//            for(Job job : jobs){
//                JobApplicationResponseDto jobApplicationResponseDto = new JobApplicationResponseDto();
//                jobApplicationResponseDto.setJob(job);
//                jobApplicationResponseDto.setJobCreationDate(job.getCreatedAt());
//                jobApplicationResponseDto.setJobStatus(job.getJobStatus());
//                userJobs.add(jobApplicationResponseDto);
//            }
//        }
//        return userJobs;
//    }

    public List<JobApplicationResponseDto> getJobApplications(String userName) {

        List<JobApplicationResponseDto> jobApplicationResponseDtos = new ArrayList<>();

        User user = userService.findUser(userName);
        List<Application> userApplications = applicationRepo.findApplicationByApplicant(user);

        for(Application application : userApplications){
            JobApplicationResponseDto jobApplicationResponseDto = new JobApplicationResponseDto();
            jobApplicationResponseDto.setJob(application.getJob());
            jobApplicationResponseDto.setApplicationStatus(application.getStatus());
            jobApplicationResponseDto.setApplicationDate(application.getApplicationDate());
            jobApplicationResponseDtos.add(jobApplicationResponseDto);
        }

        return jobApplicationResponseDtos;
    }

    public Job updateJob(Job job) {
        deleteJob(job.getJobId());
        return jobRepo.save(job);
    }

    public Job addJob(JobRequestDto jobRequestDto, String userName) {
        Job job = new Job();
        User recruiter = userService.findUser(userName);

//        job.
        job.setRecruiter(recruiter);
        job.setCreatedAt(ZonedDateTime.now());
        job.setJobStatus(JobStatus.OPEN);

        return jobRepo.save(job);
    }

    public void deleteJob(Long jobId){
        jobRepo.deleteById(jobId);
    }

}
