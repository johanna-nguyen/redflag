package com.project.redflag.report.service;

import com.project.redflag.category.entity.Category;
import com.project.redflag.category.repository.CategoryRepository;
import com.project.redflag.report.dto.request.ReportRequest;
import com.project.redflag.report.dto.response.PageResponse;
import com.project.redflag.report.dto.response.ReportResponse;
import com.project.redflag.report.entity.Report;
import com.project.redflag.report.mapper.ReportMapper;
import com.project.redflag.report.repository.ReportRepository;
import com.project.redflag.report.specification.ReportSpecification;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
    private final CategoryRepository categoryRepository;

    public ReportService(ReportRepository reportRepository, ReportMapper reportMapper, CategoryRepository categoryRepository){
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public ReportResponse createReport(ReportRequest reportRequest){
        Report report = reportMapper.toEntity(reportRequest);
        report.setCreateAt(LocalDateTime.now());
        //report.setCategory(categoryRepository.getReferenceById(reportRequest.getCategoryId()));
        report.setCategory(categoryRepository.findById(reportRequest.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not found")));
        Report saved = reportRepository.save(report);
        return reportMapper.toResponse(saved);
    }

    /*
    public List<ReportResponse> getAllReport(){
        List<Report> reportList = reportRepository.findAll();
        return reportMapper.toResponseList(reportList);
    }

    */
    public PageResponse<ReportResponse> getAllReport(Pageable pageable){
        Page<Report> reportPage = reportRepository.findAll(pageable);
        PageResponse<ReportResponse> reportResponse = new PageResponse<>();
        reportResponse.setData(reportPage.getContent().stream().map(reportMapper::toResponse).toList());
        reportResponse.setPage(reportPage.getNumber());
        reportResponse.setSize(reportPage.getSize());
        reportResponse.setTotalElements(reportPage.getTotalElements());
        reportResponse.setTotalPages(reportPage.getTotalPages());

        return reportResponse;
    }

    @Transactional
    public ReportResponse updateReport(Long id, ReportRequest updatedRequest){
        Report report = reportRepository.findById(id).orElseThrow();
        report.setFirstName(updatedRequest.getFirstName());
        report.setLastName(updatedRequest.getLastName());
        report.setAgeRange((updatedRequest.getAgeRange()));
        report.setNationality(updatedRequest.getNationality());
        report.setCity(updatedRequest.getCity());
        report.setCreateAt(LocalDateTime.now());
        report.setCategory(categoryRepository.getReferenceById(updatedRequest.getCategoryId()));
        return reportMapper.toResponse(report);
    }

    public void deleteReport(Long id){
        reportRepository.deleteById(id);
    }

    public PageResponse<ReportResponse> filterByFirstName(String firstName, Pageable pageable) {

        Page<Report> reportPage = reportRepository.findByFirstName(firstName, pageable);

        PageResponse<ReportResponse> response = new PageResponse<>();

        response.setData(
                reportPage.getContent()
                        .stream()
                        .map(reportMapper::toResponse)
                        .toList()
        );

        response.setPage(reportPage.getNumber());
        response.setSize(reportPage.getSize());
        response.setTotalElements(reportPage.getTotalElements());
        response.setTotalPages(reportPage.getTotalPages());

        return response;
    }

    public PageResponse<ReportResponse> filter(
            String firstName,
            String lastName,
            Pageable pageable) {

        Specification<Report> spec = Specification
                .where(ReportSpecification.hasFirstName(firstName))
                .and(ReportSpecification.hasLastName(lastName));

        Page<Report> reportPage = reportRepository.findAll(spec, pageable);

        PageResponse<ReportResponse> response = new PageResponse<>();

        response.setData(
                reportPage.getContent()
                        .stream()
                        .map(reportMapper::toResponse)
                        .toList()
        );

        response.setPage(reportPage.getNumber());
        response.setSize(reportPage.getSize());
        response.setTotalPages(reportPage.getTotalPages());
        response.setTotalElements(reportPage.getTotalElements());

        return response;
    }

    public PageResponse<ReportResponse> searchReport(String keyword, Pageable pageable){
        Page<Report> reportPage = reportRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(keyword, keyword, pageable);

        PageResponse<ReportResponse> response = new PageResponse<>();

        response.setData(
                reportPage.getContent()
                        .stream()
                        .map(reportMapper::toResponse)
                        .toList()
        );

        response.setPage(reportPage.getNumber());
        response.setSize(reportPage.getSize());
        response.setTotalElements(reportPage.getTotalElements());
        response.setTotalPages(reportPage.getTotalPages());

        return response;
    }

}
