package com.eraytasay.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.List;

/**
 * This class represents an authentication with username, email and password
 */
public class UEPAuthentication extends AbstractAuthenticationToken {
    private final SecurityUser user;
    private boolean authenticated;

    public UEPAuthentication(String username, String email, String password)
    {
        super(List.of());
        this.user = new SecurityUser(username, email, password);
    }

    @Override
    public Object getCredentials()
    {
        // ":" character can't be in a valid email
        return "%s:%s".formatted(user.getEmail(), user.getPassword());
    }

    @Override
    public Object getPrincipal()
    {
        return user;
    }

    @Override
    public boolean isAuthenticated()
    {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException
    {
        if (isAuthenticated)
            throw new IllegalArgumentException("The argument can only be false");

        authenticated = false;
    }

    @Override
    public String getName()
    {
        return user.getUsername();
    }

    static UEPAuthentication createAuthenticated(String username, String email)
    {
        var userAuthentication = new UEPAuthentication(username, email, null);

        userAuthentication.authenticated = true;
        return userAuthentication;
    }
}
