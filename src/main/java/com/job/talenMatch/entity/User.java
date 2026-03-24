package com.job.talenMatch.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Data
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    @Column(nullable = false)
    private String password;

//   Tells JPA to store the enum’s name (e.g., "ACTIVE") as a String in the database instead of its numeric index.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
