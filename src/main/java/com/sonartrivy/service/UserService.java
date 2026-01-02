package com.sonartrivy.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String findUser(String username) {

        // SAST: SQL Injection risk
        String query = "SELECT * FROM users WHERE name = '" + username + "'";

        // CAST: bad design, useless logic, no separation
        if (username != null) {
            if (!username.isEmpty()) {
                if (username.length() > 3) {
                    if (!username.equals("admin")) {
                        return query;
                    }
                }
            }
        }
        return "Invalid user";
    }
}
