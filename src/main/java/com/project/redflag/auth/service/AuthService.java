package com.project.redflag.auth.service;

import com.project.redflag.auth.dto.request.LoginRequest;
import com.project.redflag.auth.dto.request.RegisterRequest;
import com.project.redflag.auth.dto.response.AuthResponse;
import com.project.redflag.moderator.entity.Moderator;
import com.project.redflag.moderator.repository.ModeratorRepository;
import com.project.redflag.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final ModeratorRepository moderatorRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(ModeratorRepository moderatorRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.moderatorRepository = moderatorRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(RegisterRequest registerRequest){
        if (moderatorRepository.findByUsername(registerRequest.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists");
        }

        Moderator moderator = new Moderator();
        moderator.setUsername(registerRequest.getUsername());
        moderator.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        moderator.setRole("ADMIN");
        moderatorRepository.save(moderator);

        String message = "Register successful";
        String token = jwtUtil.generateToken(moderator.getUsername());

        return new AuthResponse(message, token);
    }

    public AuthResponse login(LoginRequest loginRequest){
        Moderator moderator = moderatorRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(()->new RuntimeException("User not found"));

        if(!passwordEncoder.matches(loginRequest.getPassword(), moderator.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        String message = "Login successful";
        String token = jwtUtil.generateToken(moderator.getUsername());
        return new AuthResponse(message,token);
    }
}
