package com.job.talenMatch.dto;

import com.job.talenMatch.entity.ProficiencyLevel;
import lombok.Data;

@Data
public class JobSkillRequest {
    private Long skillId;
    private ProficiencyLevel proficiencyLevel;
}
