package com.project.redflag.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void shouldReturn401_whenNoToken() throws Exception{
        mockMvc.perform(get("/report")).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn200_whenValidToken() throws Exception{
        String token = jwtUtil.generateToken("admin");
        
        mockMvc.perform(get("/report").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturn403_whenRoleInvalid() throws  Exception{
        String token = jwtUtil.generateToken("admin");
        mockMvc.perform(delete("/report/1").header("Authorization", "Bearer" + token)).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn401_whenTokenInvalid() throws Exception{
        String token = jwtUtil.generateToken("tester");
        mockMvc.perform((get("/report")).header("Authotization", "Bearer" + token)).andExpect(status().isUnauthorized());
    }



}
