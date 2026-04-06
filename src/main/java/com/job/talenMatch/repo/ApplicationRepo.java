package com.job.talenMatch.repo;

import com.job.talenMatch.model.Application;
import com.job.talenMatch.model.Job;
import com.job.talenMatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepo extends JpaRepository<Application, Long> {

    List<Application> findApplicationByApplicant(User applicant);

    List<Application> findApplicationByJob(Job job);
}
