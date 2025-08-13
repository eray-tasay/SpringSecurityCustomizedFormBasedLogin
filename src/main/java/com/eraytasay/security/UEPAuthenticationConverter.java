package com.eraytasay.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

@Component
public class UEPAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request)
    {
        var username = request.getParameter("username");
        var email = request.getParameter("email");
        var password = request.getParameter("password");

        return new UEPAuthentication(username, email, password);
    }
}
