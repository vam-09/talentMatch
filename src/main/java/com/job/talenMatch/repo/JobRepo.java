package com.job.talenMatch.repo;

import com.job.talenMatch.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepo extends JpaRepository<Job, Long> {
    Job findJobByJobId(Long jobId);
}
