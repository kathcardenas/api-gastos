package com.example.gastos.gastos.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiErrorResponse {
    private int code;
    private String message;

    
}
