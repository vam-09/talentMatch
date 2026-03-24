package com.job.talenMatch.repository;

import com.job.talenMatch.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
