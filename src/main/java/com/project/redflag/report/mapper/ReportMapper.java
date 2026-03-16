package com.project.redflag.report.mapper;


import com.project.redflag.report.dto.request.ReportRequest;
import com.project.redflag.report.dto.response.ReportResponse;
import com.project.redflag.report.entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ReportMapper {

    //convert report request to report
    Report toEntity(ReportRequest reportRequest);

    //convert report to report response
    @Mapping(source="category.id", target = "categoryId")
    ReportResponse toResponse(Report report);

    // convert list Report to List Report Response
    List<ReportResponse> toResponseList(List<Report> reportList);
}
