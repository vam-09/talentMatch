package com.job.talenMatch.repo;

import com.job.talenMatch.model.Job;
import com.job.talenMatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepo extends JpaRepository<Job, Long> {

    Job findJobByJobId(Long jobId);

    @Query(value = "SELECT * FROM jobs where " +
            "lower(company_name) like lower(concat('%', :keyword, '%')) or " +
            "lower(job_title) like lower(concat('%', :keyword, '%')) or " +
            "lower(job_description) like lower(concat('%', :keyword, '%')) or " +
            "lower(required_skills) like lower(concat('%', :keyword, '%'))", nativeQuery = true)
    List<Job> findByKeyword(String keyword);

    List<Job> findJobByRecruiter(User recruiter);
}
