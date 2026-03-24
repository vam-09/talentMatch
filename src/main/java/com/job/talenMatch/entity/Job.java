package com.job.talenMatch.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String jobTitle;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String jobDescription;

    private String locationCity;

    @Enumerated(EnumType.STRING)
    private WorkModel workModel; // Remote, Hybrid, WFO

    // BigDecimal is best practice for Currency to avoid floating point errors
    private BigDecimal ctc;

    @Enumerated(EnumType.STRING)
    private Shift shift; // Morning, Night

    private String workDays; // e.g., "Mon-Fri" or "5 Days"

    private String educationRequirement;

    private String experience; // String allows "2-4 Years", Double for specific numbers

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus jobStatus;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDate applicationDeadline;
}