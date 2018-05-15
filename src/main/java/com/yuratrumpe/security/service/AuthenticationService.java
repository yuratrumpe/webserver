package com.yuratrumpe.security.service;

import com.yuratrumpe.model.User;
import com.yuratrumpe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userService.getUserByName(s);

        if (user == null) {
            throw new UsernameNotFoundException("Username " + s + " not found");
        }

        return user;
    }
}
