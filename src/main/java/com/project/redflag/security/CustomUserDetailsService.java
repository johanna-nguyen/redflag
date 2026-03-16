package com.project.redflag.security;

import com.project.redflag.moderator.entity.Moderator;
import com.project.redflag.moderator.repository.ModeratorRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final ModeratorRepository moderatorRepository;

    public CustomUserDetailsService(ModeratorRepository moderatorRepository){
        this.moderatorRepository = moderatorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Moderator moderator = moderatorRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new org.springframework.security.core.userdetails.User(
                moderator.getUsername(),
                moderator.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + moderator.getRole()))
        );
    }
}
