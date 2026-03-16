package com.project.redflag.controller;

import com.project.redflag.report.controller.ReportController;
import com.project.redflag.report.dto.response.PageResponse;
import com.project.redflag.report.dto.response.ReportResponse;
import com.project.redflag.report.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.project.redflag.security.JwtFilter;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = ReportController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtFilter.class)
)
@AutoConfigureMockMvc(addFilters = false)
class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReportService reportService;

    @Test
    void getAllReport_shouldReturnReportPage() throws Exception {
        PageResponse<ReportResponse> reports = new PageResponse<>();
        /*
        reports.setData(List.of());
        reports.setPage(0);
        reports.setSize(5);
        reports.setTotalElements(0L);
        reports.setTotalPages(0);

         */

        when(reportService.getAllReport(any(Pageable.class))).thenReturn(reports);

        mockMvc.perform(get("/report"))
                .andExpect(status().isOk());
                /*
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(5))
                .andExpect(jsonPath("$.totalElements").value(0))
                .andExpect(jsonPath("$.totalPages").value(0));

                 */

    }
    @Test
    void createReport_shouldReturnReportResponse() throws Exception {
        ReportResponse response = new ReportResponse();
        response.setId(1L);

        when(reportService.createReport(any())).thenReturn(response);

        mockMvc.perform(post("/report")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "firstName": "d",
                      "lastName": "r",
                      "ageRange": "38",
                      "nationality": "testing",
                      "city": "testing",
                      "categoryId": 1
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }
}
