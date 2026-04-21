package com.job.talenMatch.repo;

import com.job.talenMatch.model.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSkillRepo extends JpaRepository<JobSkill, Long> {
}
