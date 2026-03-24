package com.job.talenMatch.dto;

import com.job.talenMatch.entity.JobStatus;
import com.job.talenMatch.entity.Shift;
import com.job.talenMatch.entity.WorkModel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class JobRequest {
    private Long userId;
    private String companyName;
    private String jobTitle;
    private String jobDescription;
    private String locationCity;
    private WorkModel workModel;
    private BigDecimal ctc;
    private Shift shift;
    private String workDays;
    private String educationRequirement;
    private String experience;
    private JobStatus jobStatus;
    private LocalDate applicationDeadline;

    private List<JobSkillRequest> skills;
}
