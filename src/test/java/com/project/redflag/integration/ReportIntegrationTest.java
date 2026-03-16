package com.project.redflag.integration;

import com.project.redflag.category.entity.Category;
import com.project.redflag.category.repository.CategoryRepository;
import com.project.redflag.report.repository.ReportRepository;
import com.project.redflag.security.CustomUserDetailsService;
import com.project.redflag.security.JwtUtil;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ReportIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @MockitoBean
    private CustomUserDetailsService userDetailsService;

    @Test
    void createReport_shouldSaveReport() throws Exception {
        long before = reportRepository.count();

        Category category = new Category();
        category.setName("test-category");
        category = categoryRepository.save(category);

        when(userDetailsService.loadUserByUsername("admin"))
                .thenReturn(User.withUsername("admin").password("pw").roles("ADMIN").build());

        String token = jwtUtil.generateToken("admin");

        String body = """
                {
                  "firstName": "D",
                  "lastName": "R",
                  "ageRange": "38",
                  "nationality": "test",
                  "city": "test",
                  "categoryId": %d
                }
                """.formatted(category.getId());

        mockMvc.perform(post("/report")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());

        assertEquals(before + 1, reportRepository.count());
    }

}
