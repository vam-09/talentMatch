package com.job.talenMatch.services;

import com.job.talenMatch.dto.JobRequest;
import com.job.talenMatch.dto.JobSkillRequest;
import com.job.talenMatch.entity.Job;
import com.job.talenMatch.entity.JobSkills;
import com.job.talenMatch.entity.Skills;
import com.job.talenMatch.repository.JobRepository;
import com.job.talenMatch.repository.JobSkillsRepository;
import com.job.talenMatch.repository.SkillsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {
    private static final Logger log = LoggerFactory.getLogger(com.job.talenMatch.services.JobService.class);

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobSkillsRepository jobSkillsRepository;

    @Autowired
    private SkillsRepository skillsRepository;
    public void createJob(JobRequest request) {

        // 1️⃣ Save Job first
        Job job = Job.builder()
                .userId(request.getUserId())
                .companyName(request.getCompanyName())
                .jobTitle(request.getJobTitle())
                .jobDescription(request.getJobDescription())
                .locationCity(request.getLocationCity())
                .workModel(request.getWorkModel())
                .ctc(request.getCtc())
                .shift(request.getShift())
                .workDays(request.getWorkDays())
                .educationRequirement(request.getEducationRequirement())
                .experience(request.getExperience())
                .jobStatus(request.getJobStatus())
                .applicationDeadline(request.getApplicationDeadline())
                .build();

        Job savedJob = jobRepository.save(job);

        // 2️⃣ Save JobSkills
        List<JobSkills> jobSkillsList = new ArrayList<>();

        for (JobSkillRequest skillReq : request.getSkills()) {

            Skills skill = skillsRepository.findById(skillReq.getSkillId())
                    .orElseThrow(() -> new RuntimeException("Skill not found"));

            JobSkills jobSkill = JobSkills.builder()
                    .job(savedJob)
                    .skills(skill)
                    .proficiencyLevel(skillReq.getProficiencyLevel())
                    .build();

            jobSkillsList.add(jobSkill);
        }

        jobSkillsRepository.saveAll(jobSkillsList);
    }


}
