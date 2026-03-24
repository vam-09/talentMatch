package com.job.talenMatch.controller;

import com.job.talenMatch.dto.SkillRequest;
import com.job.talenMatch.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skills")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @PostMapping("/addSkill")
    private String addNewSkill(SkillRequest skillRequest){
        skillService.addSkill(skillRequest);
        return "Skill added successfully";
    }

    private final String SKILL_REQUEST_JSON = """
            {
              "skillName": "Java"
            }
            """;

}
