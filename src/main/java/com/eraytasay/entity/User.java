package com.eraytasay.entity;

import java.time.LocalDate;

public class User {
    private final String username;
    private final String email;
    private final String password;
    private final LocalDate birthDate;

    public User(String username, String email, String password, LocalDate birthDate)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }

    public String getUsername()
    {
        return username;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }
}
