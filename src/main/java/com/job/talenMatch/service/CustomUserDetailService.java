package com.job.talenMatch.service;

import com.job.talenMatch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailService(UserService userService){
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUser(username);
        UserDetails userDetailsUser = org.springframework.security.core.userdetails.User.builder().username(username).password(user.getPassword()).build();

         return userDetailsUser;
    }
}
