package com.project.redflag.report.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportRequest {

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]$", message = "must be exactly 1 letter (A-Z or a-z)")
    @Schema(example = "D")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]$", message = "must be exactly 1 letter (A-Z or a-z)")
    @Schema(example = "R")
    private String lastName;

    @NotBlank
    @Schema(example = "40")
    private String ageRange;

    @NotBlank
    @Schema(example = "Hawaii")
    private String city;

    @NotBlank
    @Schema(example = "American")
    private String nationality;

    @NotNull
    @Schema(example = "1")
    private Long categoryId;

}
