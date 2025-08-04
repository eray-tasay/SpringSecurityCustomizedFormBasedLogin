package com.eraytasay.config;

import com.eraytasay.security.UEPAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final UEPAuthenticationFilter UEPAuthenticationFilter;

    public SecurityConfig(UEPAuthenticationFilter UEPAuthenticationFilter)
    {
        this.UEPAuthenticationFilter = UEPAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .securityContext(context -> context.requireExplicitSave(false))
                .formLogin(c -> c
                    .loginPage("/myLogin")
                    .permitAll())
                .logout(c -> c
                    .logoutUrl("/doLogout")
                    .logoutSuccessUrl("/myLogin")
                    .permitAll())
                .addFilterAt(UEPAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(c -> c
                    .requestMatchers("/myLogin", "/css/**", "/js/**", "/auth-test").permitAll()
                    .anyRequest().authenticated());

        return httpSecurity.build();
    }
}
