package com.job.talenMatch.service;

import com.job.talenMatch.dto.JobApplicationResponseDto;
import com.job.talenMatch.dto.JobRequestDto;
import com.job.talenMatch.model.*;
import com.job.talenMatch.repo.ApplicationRepo;
import com.job.talenMatch.repo.JobRepo;
import com.job.talenMatch.repo.JobSkillRepo;
import com.job.talenMatch.repo.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JobService {

    private final JobRepo jobRepo;
    private final UserService userService;
    private final ApplicationRepo applicationRepo;
    private final JobSkillRepo jobSkillRepo;
    private final SkillRepo skillRepo;
    private final EmailService emailService;
    private final SmsService smsService;

    @Autowired
    JobService(JobRepo jobRepo, UserService userService, ApplicationRepo applicationRepo, JobSkillRepo jobSkillRepo, SkillRepo skillRepo, EmailService emailService, SmsService smsService){
        this.jobRepo = jobRepo;
        this.userService = userService;
        this.applicationRepo = applicationRepo;
        this.jobSkillRepo = jobSkillRepo;
        this.skillRepo = skillRepo;
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public Job findJob(Long jobId) {
        return jobRepo.findJobByJobId(jobId);
    }

    public List<Job> findAllJobs() {
        List<Job> jobs = jobRepo.findAll();
        jobs.sort((j1, j2) -> {
            if(j1.getJobStatus() == JobStatus.OPEN && j2.getJobStatus() != JobStatus.OPEN) return -1;
            if(j1.getJobStatus() != JobStatus.OPEN && j2.getJobStatus() == JobStatus.OPEN) return 1;
            
            // Secondary sort by date (Newest first)
            if (j1.getCreatedAt() != null && j2.getCreatedAt() != null) {
                return j2.getCreatedAt().compareTo(j1.getCreatedAt());
            }
            return 0;
        });
        return jobs;
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

    public Job updateJob(Job updatedJob) {
        // 1. Fetch the existing job from the DB
        Job existingJob = jobRepo.findById(updatedJob.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + updatedJob.getJobId()));

        // 2. Update only the fields that are allowed to change from the UI
        existingJob.setJobTitle(updatedJob.getJobTitle());
        existingJob.setCompanyName(updatedJob.getCompanyName());
        existingJob.setJobStatus(updatedJob.getJobStatus());
        existingJob.setJobDescription(updatedJob.getJobDescription());
        existingJob.setRequiredSkills(updatedJob.getRequiredSkills());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setCtc(updatedJob.getCtc());
        existingJob.setWorkModel(updatedJob.getWorkModel());
        existingJob.setShift(updatedJob.getShift());
        existingJob.setWorkDays(updatedJob.getWorkDays());
        existingJob.setEducationRequirement(updatedJob.getEducationRequirement());
        existingJob.setExperience(updatedJob.getExperience());
        existingJob.setApplicationDeadline(updatedJob.getApplicationDeadline());

        // 3. Save the existingJob (JPA will perform an UPDATE)
        return jobRepo.save(existingJob);
    }

    public Job addJob(JobRequestDto jobRequestDto, String userName) {
        Job job = new Job();
        User recruiter = userService.findUser(userName);

        job.setJobDescription(jobRequestDto.getJobDescription());
        job.setJobTitle(jobRequestDto.getJobTitle());
        job.setApplicationDeadline(jobRequestDto.getApplicationDeadline());
        job.setCompanyName(jobRequestDto.getCompanyName());
        job.setRequiredSkills(jobRequestDto.getRequiredSkills());
        job.setEducationRequirement(jobRequestDto.getEducationRequirement());
        job.setCtc(jobRequestDto.getCtc());
        job.setLocation(jobRequestDto.getLocation());
        job.setExperience(jobRequestDto.getExperience());
        job.setShift(jobRequestDto.getShift());
        job.setWorkDays(jobRequestDto.getWorkDays());
        job.setWorkModel(jobRequestDto.getWorkModel());

        job.setRecruiter(recruiter);
        job.setCreatedAt(ZonedDateTime.now());
        job.setJobStatus(JobStatus.OPEN);

        Set<Skill> requiredJobSkills = fetchSkills(job.getRequiredSkills());
        job.setSkills(requiredJobSkills);

        List<User> matchingUsers = userService.findMatchingUsers(requiredJobSkills);
        sendEmail(matchingUsers, job);
        sendSMS(matchingUsers, job);

        return jobRepo.save(job);
    }

    public void sendEmail(List<User> userList, Job job){
        emailService.sendMail(userList, job);

    }

    public Set<Skill> fetchSkills(String skills){
        String[] skillArray = skills.split(",");
        Set<Skill> skillSet = new HashSet<>();
        for(String skill : skillArray){
            skillRepo.findBySkillName(skill.trim()).ifPresent(skillSet::add);
        }
        return skillSet;
    }

    public void sendSMS(List<User> userList, Job job){
        smsService.sendSms(userList, job);
    }


    public void deleteJob(Long jobId){
        jobRepo.deleteById(jobId);
    }

    public Job getJob(Long jobId) {
        return jobRepo.findJobByJobId(jobId);
    }
}
