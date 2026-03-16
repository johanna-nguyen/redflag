package com.project.redflag.report.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<R> {

    private List<R> data;

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;
}
