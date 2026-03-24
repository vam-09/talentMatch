package com.job.talenMatch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "JobSkills")
public class JobSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long ID;
    @ManyToOne
    @JoinColumn(name = "job_id")
    public Job job;
    @ManyToOne
    @JoinColumn(name = "skill_id")
    public Skills skills;
    public ProficiencyLevel proficiencyLevel;
}
