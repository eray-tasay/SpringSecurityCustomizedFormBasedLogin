package com.eraytasay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
    @GetMapping("/home")
    public String home()
    {
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
