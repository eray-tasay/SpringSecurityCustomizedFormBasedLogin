package com.eraytasay.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * This class does the authentication logic comparing username, email and password
 */
@Component
public class UEPAuthenticationProvider implements AuthenticationProvider {
    private static final Logger LOG = Logger.getLogger(UEPAuthenticationProvider.class.getName());

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public UEPAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder)
    {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
    {
        if (!supports(authentication.getClass()))
            return null;

        var authenticationUser = (SecurityUser) authentication.getPrincipal();
        var userDetailsServiceUser = (SecurityUser) userDetailsService.loadUserByUsername(authenticationUser.getUsername());

        if (authenticationUser.getEmail().equals(userDetailsServiceUser.getEmail())
                && passwordEncoder.matches(authenticationUser.getPassword(), userDetailsServiceUser.getPassword())) {
            LOG.info("Authentication for user %s is successful".formatted(userDetailsServiceUser.getUsername()));

            return UEPAuthentication.createAuthenticated(userDetailsServiceUser.getUsername(), userDetailsServiceUser.getEmail());
        }

        LOG.info("Authentication for user %s is failed".formatted(userDetailsServiceUser.getUsername()));
        throw new BadCredentialsException("Wrong email or password");
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return authentication.equals(UEPAuthentication.class);
    }
}
