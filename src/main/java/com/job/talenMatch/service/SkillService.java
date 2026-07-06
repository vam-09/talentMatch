package com.job.talenMatch.service;

import com.job.talenMatch.model.Skill;
import com.job.talenMatch.model.User;
import com.job.talenMatch.model.UserSkill;
import com.job.talenMatch.repo.JobSkillRepo;
import com.job.talenMatch.repo.SkillRepo;
import com.job.talenMatch.repo.UserSkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {

    private final SkillRepo skillRepo;
    private final UserSkillRepo userSkillRepo;
    private final JobSkillRepo jobSkillRepo;

    @Autowired
    public SkillService(SkillRepo skillRepo, UserSkillRepo userSkillRepo, JobSkillRepo jobSkillRepo){
        this.userSkillRepo = userSkillRepo;
        this.skillRepo = skillRepo;
        this.jobSkillRepo = jobSkillRepo;
    }

    public List<Skill> getSkills(){
        return skillRepo.findAll();
    }

    public void addUserSkills(List<Skill> skills, User user) {
        List<UserSkill> userSkills = new ArrayList<>();
        skills.forEach(skill -> {
            userSkills.add(UserSkill.builder().skill(skill).user(user).build());
        });

        userSkillRepo.saveAll(userSkills);
    }

    public void addJobSkills(String skills) {
        String[] skillArray = skills.split(",");
        List<Skill> skillList = new ArrayList<>();
        for(String skill : skillArray){
            skillRepo.findBySkillName(skill).ifPresent(skillList::add);
        }
    }
}
