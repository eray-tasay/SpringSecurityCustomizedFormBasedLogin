package com.eraytasay.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class TestController {
    private static final Logger LOG = Logger.getLogger(TestController.class.getName());

    @GetMapping("/auth-test")
    public String testAuth()
    {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        LOG.info("Current auth: " + auth);
        LOG.info("Auth class: " + (auth != null ? auth.getClass().getName() : "null"));
        LOG.info("Is authenticated: " + (auth != null ? auth.isAuthenticated() : "null"));
        LOG.info("Principal: " + (auth != null ? auth.getPrincipal() : "null"));
        LOG.info("Authorities: " + (auth != null ? auth.getAuthorities() : "null"));
        
        return "Auth details logged";
    }
}
