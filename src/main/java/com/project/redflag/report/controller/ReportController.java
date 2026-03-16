package com.project.redflag.report.controller;

import com.project.redflag.report.dto.request.ReportRequest;
import com.project.redflag.report.dto.response.PageResponse;
import com.project.redflag.report.dto.response.ReportResponse;
import com.project.redflag.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'REPORTER')")
    @PostMapping
    @Operation(summary = "create a report")
    @Tag(name = "Report API")
    public ReportResponse createReport(@Valid @RequestBody ReportRequest reportRequest){
        return reportService.createReport(reportRequest);
    }

    /*
    @GetMapping
    public List<ReportResponse> getAllReport(){
        return reportService.getAllReport();
    }

     */
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'REPORTER')")
    @GetMapping
    @Operation(summary = "get all report")
    @Tag(name = "Report API")
    public PageResponse<ReportResponse> getAllReport(@PageableDefault(size=5, sort="id") Pageable pageable){
        return reportService.getAllReport(pageable);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PutMapping ("/{id}")
    @Operation(summary = "update a report by id")
    @Tag (name = "Report API")
    public ReportResponse updateReport(@PathVariable Long id, @RequestBody ReportRequest reportRequest){
        return reportService.updateReport(id, reportRequest);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "delete a report")
    @Tag (name = "Report API")
    public void deleteReport(@PathVariable Long id){
        reportService.deleteReport(id);
    }

    /*
    @GetMapping("/filter")
    @Operation (summary = "filter a report by first name")
    @Tag(name = "Report API")
    public PageResponse<ReportResponse> filterByFirstName(String firstName, Pageable pageable){
        return reportService.filterByFirstName(firstName, pageable);
    }

     */

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'REPORTER')")
    @GetMapping("/filter")
    @Operation(summary = "filter a report by first name and last name")
    @Tag (name="Report API")
    public PageResponse<ReportResponse> filter(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            Pageable pageable
    ) {

        return reportService.filter(firstName, lastName, pageable);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'REPORTER')")
    @GetMapping("/search")
    @Operation(summary = "search a report by keyword")
    @Tag (name = "Report API")
    public PageResponse<ReportResponse> search(String keyword, Pageable pageable){
        return reportService.searchReport(keyword, pageable);
    }

}
