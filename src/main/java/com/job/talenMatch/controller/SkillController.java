package com.job.talenMatch.controller;

import com.job.talenMatch.model.Skill;
import com.job.talenMatch.repo.SkillRepo;
import com.job.talenMatch.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SkillController {

    private final SkillService skillService;

    @Autowired
    SkillController(SkillService skillService){
        this.skillService = skillService;
    }

    @GetMapping("/skills")
    public List<Skill> getSkills(){
        return skillService.getSkills();
    }

//    @PostMapping("/userSkills")
//    public void addUserSkills(){
//        skillService.addUserSkills();
//    }

}
