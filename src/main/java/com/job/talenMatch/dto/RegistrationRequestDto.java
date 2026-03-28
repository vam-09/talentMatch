package com.job.talenMatch.dto;

import com.job.talenMatch.model.Skill;
import com.job.talenMatch.model.User;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationRequestDto {
    private User user;
    private List<Skill> skills;
}
