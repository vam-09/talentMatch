package com.job.talenMatch.services;

import com.job.talenMatch.dto.SkillRequest;
import com.job.talenMatch.entity.Skills;
import com.job.talenMatch.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SkillService {
    @Autowired
    private SkillsRepository skillsRepository;

    public void addSkill(SkillRequest skillRequest){
        Skills newSkill = Skills.builder().skillName(skillRequest.getSkillName()).build();
        skillsRepository.save(newSkill);
    }
}
