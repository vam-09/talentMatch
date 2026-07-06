package com.job.talenMatch.repo;

import com.job.talenMatch.model.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSkillRepo extends JpaRepository<UserSkill, Long> {
}
