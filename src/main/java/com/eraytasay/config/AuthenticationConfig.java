package com.eraytasay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
public class AuthenticationConfig {
    private final AuthenticationConverter authenticationConverter;

    @Value("${login.url}")
    private String loginUrl;

    @Value("${login.defaultSuccessUrl}")
    private String defaultSuccessUrl;

    @Value("${login.defaultFailureUrl}")
    private String defaultFailureUrl;

    public AuthenticationConfig(AuthenticationConverter authenticationConverter)
    {
        this.authenticationConverter = authenticationConverter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider)
    {
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public AuthenticationFilter authenticationFilter(AuthenticationManager authenticationManager)
    {
        var filter = new AuthenticationFilter(authenticationManager, authenticationConverter);
        var successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        var failureHandler = new SimpleUrlAuthenticationFailureHandler(defaultFailureUrl);

        successHandler.setDefaultTargetUrl(defaultSuccessUrl);

        filter.setRequestMatcher(PathPatternRequestMatcher.withDefaults().matcher(loginUrl));
        filter.setSuccessHandler(successHandler);
        filter.setFailureHandler(failureHandler);

        return filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
}
