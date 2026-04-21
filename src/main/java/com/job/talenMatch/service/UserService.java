package com.job.talenMatch.service;

import com.job.talenMatch.model.Skill;
import com.job.talenMatch.model.User;
import com.job.talenMatch.model.UserRole;
import com.job.talenMatch.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(ZonedDateTime.now());
        user.setUpdatedAt(ZonedDateTime.now());
        return userRepo.save(user);
    }

    public User findUser(String userName){
        return userRepo.findByUserName(userName);
    }

    public User updateUser(User user) {
        User existingUser = userRepo.findByUserName(user.getUserName());
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setUpdatedAt(ZonedDateTime.now());
            return userRepo.save(existingUser);
        } else {
            return null;
        }
    }

    public List<User> findMatchingUsers(Set<Skill> requiredJobSkills) {

        List<User> allUsers = userRepo.findUserByRole(UserRole.APPLICANT);
        List<User> matchingUsers = new ArrayList<>();

        for(User user : allUsers){
            double score = calculateSimilarity(requiredJobSkills, user.getSkills());
            if(score >= 0.8){
                matchingUsers.add(user);
            }
        }
        return matchingUsers;
    }

    private double calculateSimilarity(Set<Skill> set1, Set<Skill> set2) {
        if (set1 == null || set1.isEmpty() || set2 == null || set2.isEmpty()) {
            return 0.0;
        }
        Set<Skill> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<Skill> union = new HashSet<>(set1);
        union.addAll(set2);

        return (double) intersection.size() / union.size();
    }
}
