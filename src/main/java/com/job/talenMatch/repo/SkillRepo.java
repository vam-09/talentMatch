package com.job.talenMatch.repo;

import com.job.talenMatch.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Long> {
    Optional<Skill> findBySkillName(String skill);
}
