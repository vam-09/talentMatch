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
        // The .roles() method is a convenience helper. It takes your raw role name (e.g., APPLICANT), adds ROLE_ to the front, and stores it as a GrantedAuthority that Spring Security can use for permission checks.
        UserDetails userDetailsUser = org.springframework.security.core.userdetails.User.builder().username(username).roles(user.getRole().name()).password(user.getPassword()).build();

         return userDetailsUser;
    }
}
