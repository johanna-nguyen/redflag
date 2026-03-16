package com.project.redflag.report;

import com.project.redflag.category.entity.Category;
import com.project.redflag.category.repository.CategoryRepository;
import com.project.redflag.report.repository.ReportRepository;
import com.project.redflag.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReportControllerPersistenceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void createReport_shouldSaveReport() throws Exception {
        long before = reportRepository.count();

        Category category = new Category();
        category.setName("test-category");
        category = categoryRepository.save(category);

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
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber());

        assertEquals(before + 1, reportRepository.count());
    }
}

