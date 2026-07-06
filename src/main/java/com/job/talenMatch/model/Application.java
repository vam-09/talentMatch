package com.job.talenMatch.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "applications", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "job_id"})
})
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User applicant;

    @ManyToOne
    @JoinColumn(name = "resume_id", nullable = true) // Made nullable
    private Resume resume;

    @CreationTimestamp
    private ZonedDateTime applicationDate;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
