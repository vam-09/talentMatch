package com.job.talenMatch.repo;

import com.job.talenMatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
