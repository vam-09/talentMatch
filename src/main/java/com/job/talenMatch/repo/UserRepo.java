package com.job.talenMatch.repo;

import com.job.talenMatch.model.Skill;
import com.job.talenMatch.model.User;
import com.job.talenMatch.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserName(String userName);

//    List<User> findMatchingUsers(Set<Skill> requiredJobSkills);

    List<User> findUserBySkillsIn(Set<Skill> requiredJobSkills);

    List<User> findUserByRole(UserRole userRole);
}
