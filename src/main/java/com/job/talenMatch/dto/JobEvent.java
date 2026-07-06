package com.job.talenMatch.dto;

import com.job.talenMatch.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobEvent implements Serializable { // Implement Serializable for Kafka

    private Long jobId;
    private String jobTitle;
    private String companyName;
    private User recruiter;
    private List<UserToNotify> usersToNotify; // List of users who match the job

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserToNotify implements Serializable {
        private String userName;
        private String email;
        private String phoneNumber;
    }
}
