package com.project.redflag.service;

import com.project.redflag.category.entity.Category;
import com.project.redflag.category.repository.CategoryRepository;
import com.project.redflag.report.dto.request.ReportRequest;
import com.project.redflag.report.dto.response.ReportResponse;
import com.project.redflag.report.entity.Report;
import com.project.redflag.report.mapper.ReportMapper;
import com.project.redflag.report.repository.ReportRepository;
import com.project.redflag.report.service.ReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {
    @Mock
    private ReportRepository reportRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ReportMapper reportMapper;

    @InjectMocks
    private ReportService reportService;

    @Test
    void createReport_shouldReturnReportResponse() {
        ReportRequest request = new ReportRequest();
        request.setCategoryId(1L);

        Category category = new Category();
        category.setId(1L);

        Report report = new Report();

        Report savedReport = new Report();
        savedReport.setId(1L);

        ReportResponse response = new ReportResponse();
        response.setId(1L);

        //when(categoryRepository.getReferenceById(1L)).thenReturn((category));

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        when(reportMapper.toEntity(request)).thenReturn(report);

        when(reportRepository.save(report)).thenReturn(savedReport);

        when(reportMapper.toResponse(savedReport)).thenReturn(response);

        ReportResponse result = reportService.createReport(request);

        assertNotNull(result);

        assertEquals(1L, result.getId());

        //verify(categoryRepository).getReferenceById(1L);

        verify(categoryRepository).findById(1L);
        verify(reportRepository).save(report);
    }

    @Test
    void createReport_shouldThrowException_whenCategoryNotFound(){
        ReportRequest request = new ReportRequest();
        request.setCategoryId(99L);

        Report report = new Report();
        when(reportMapper.toEntity(request)).thenReturn(report);
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, ()-> reportService.createReport(request));
        assertEquals("Category not found", ex.getMessage());

        verify(categoryRepository).findById(99L);
        verify(reportRepository, never()).save(report);
    }


}




