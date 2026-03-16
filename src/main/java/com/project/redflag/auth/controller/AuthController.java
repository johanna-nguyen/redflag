package com.project.redflag.auth.controller;

import com.project.redflag.auth.dto.request.LoginRequest;
import com.project.redflag.auth.dto.request.RegisterRequest;
import com.project.redflag.auth.dto.response.AuthResponse;
import com.project.redflag.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private  final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(summary = "register an user")
    @Tag(name = "Auth API")
    public AuthResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    @Operation(summary = "Login an account")
    @Tag(name ="Auth API")
    public AuthResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
