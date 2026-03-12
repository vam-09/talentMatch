package com.job.talenMatch.service;

import com.job.talenMatch.model.User;
import com.job.talenMatch.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    UserRepo userRepo;

    public void registerUser(User user){
        userRepo.save(user);

    }
}
