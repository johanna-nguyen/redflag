package com.project.redflag.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIErrorResponse {
    private int status;
    private  String message;
    private long timestamp;
}
