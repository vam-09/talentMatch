package com.job.talenMatch.dto;

import com.job.talenMatch.model.ApplicationStatus;
import com.job.talenMatch.model.Job;
import com.job.talenMatch.model.JobStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
public class JobApplicationResponseDto {
    private Job job;
    private ApplicationStatus applicationStatus;
    private ZonedDateTime applicationDate;
    private ZonedDateTime jobCreationDate;
    private JobStatus jobStatus;
}
