package com.eraytasay.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class UEPAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final Logger LOG = Logger.getLogger(UEPAuthenticationFilter.class.getName());

    public UEPAuthenticationFilter(@Value("${login.url}") String loginUrl, @Value("${login.defaultSuccessUrl}") String defaultSuccessUrl,
                                   @Value("${login.defaultFailureUrl}") String defaultFailureUrl, AuthenticationManager authenticationManager)
    {
        super(loginUrl, authenticationManager);

        var authenticationSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        var authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler(defaultFailureUrl);

        authenticationSuccessHandler.setDefaultTargetUrl(defaultSuccessUrl);

        super.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        super.setAuthenticationFailureHandler(authenticationFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {
        var username = request.getParameter("username");
        var email = request.getParameter("email");
        var password = request.getParameter("password");

        LOG.info("Authentication request arrived: (username=%s, email=%s, password=%s)".formatted(username, email, password));

        var UEPAuthentication = new UEPAuthentication(username, email, password);
        var authenticationManager = super.getAuthenticationManager();

        return authenticationManager.authenticate(UEPAuthentication);
    }
}
