package com.eraytasay.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyInMemoryUserDetailsService implements UserDetailsService {
    private final SecurityUser[] users = new SecurityUser[2];

    public MyInMemoryUserDetailsService()
    {
        users[0] = new SecurityUser("eray", "eray.tasay@hotmail.com", "1234");
        users[1] = new SecurityUser("ahmet", "ahmet.tasay@hotmail.com", "12345");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return Arrays.stream(users).filter(user -> user.getUsername().equals(username)).findFirst().orElseThrow(() ->
                new UsernameNotFoundException("No such username: %s".formatted(username)));
    }
}
