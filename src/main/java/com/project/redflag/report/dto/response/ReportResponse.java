package com.project.redflag.report.dto.response;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String ageRange;

    private String city;

    private String nationality;

    private LocalDateTime createAt;

    private Long categoryId;

}
