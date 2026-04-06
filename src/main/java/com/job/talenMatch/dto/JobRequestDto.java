package com.job.talenMatch.dto;

import com.job.talenMatch.model.JobStatus;
import com.job.talenMatch.model.Shift;
import com.job.talenMatch.model.User;
import com.job.talenMatch.model.WorkModel;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

public class JobRequestDto {

    private String companyName;
    private String jobTitle;
    private String jobDescription;
    private String requiredSkills;
    private String location;
    private WorkModel workModel;
    private String ctc;
    private Shift shift;
    private String workDays;
    private String educationRequirement;
    private String experience;
    private ZonedDateTime applicationDeadline;
}
