package com.job.talenMatch.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User recruiter;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String jobTitle;

    @Column(columnDefinition = "TEXT")
    private String jobDescription;

    private String requiredSkills;

    private String location;

    @Enumerated(EnumType.STRING)
    private WorkModel workModel;

    private String ctc;

    @Enumerated(EnumType.STRING)
    private Shift shift;

    private String workDays;

    private String educationRequirement;

    private String experience;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    private ZonedDateTime applicationDeadline;

    @ManyToMany
    @JoinTable(
            name = "job_skills",
            joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills = new HashSet<>();
}
