package com.gogibibo.goibibo.service;

import com.gogibibo.goibibo.model.User;
import com.gogibibo.goibibo.model.UserPrinciple;
import com.gogibibo.goibibo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepository.findByUsername(username);
        if(byUsername==null)
        {
            System.out.println("User Not found");
        }

        return new UserPrinciple(byUsername);
    }
}
