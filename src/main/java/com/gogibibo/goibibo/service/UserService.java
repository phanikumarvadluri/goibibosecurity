package com.gogibibo.goibibo.service;


import com.gogibibo.goibibo.model.User;
import com.gogibibo.goibibo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;


    public User save(User user) {
        return userRepository.save(user);
    }

    public String verify(User user) {

        boolean authenticated=false;
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            authenticated= authenticate.isAuthenticated();
        }
        catch(Exception e)
        {
            return "Faillllllleddd";
        }
        if(authenticated)
        {
            return jwtService.genetateToken(user.getUsername());
        }
        else{
            return "Fail";
        }
    }
}
