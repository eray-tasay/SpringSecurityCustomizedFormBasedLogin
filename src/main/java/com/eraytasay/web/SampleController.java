package com.eraytasay.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
    @GetMapping("/home")
    public String home(Authentication authentication, Model model)
    {
        model.addAttribute("username", authentication.getName());

        return "home.html";
    }

    @GetMapping("/myLogin")
    public String myLogin()
    {
        return "login.html";
    }

    @GetMapping("/myLogout")
    public String myLogout()
    {
        return "logout.html";
    }
}
