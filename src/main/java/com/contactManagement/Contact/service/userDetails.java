package com.contactManagement.Contact.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class userDetails implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(username.equals("user")){
            return new User("user","ZWJsb29kYmFua05pY3RyQDEyMw==", new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User Not Found Exception");
        }
    }
}
