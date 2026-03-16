package com.sakshi.authentication_service.controller;

import com.sakshi.authentication_service.dto.LoginRequest;
import com.sakshi.authentication_service.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public String register(@RequestBody LoginRequest request) {
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {


        if ("sakshi".equals(request.getUsername()) &&
                "password".equals(request.getPassword())) {

            return AuthService.generateToken(request.getUsername());
        }

        throw new RuntimeException("Invalid credentials");
    }
}
