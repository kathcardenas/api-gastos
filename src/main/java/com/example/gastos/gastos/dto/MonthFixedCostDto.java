package com.example.gastos.gastos.dto;

import lombok.Data;

@Data
public class MonthFixedCostDto {
    private String name;
    private Double amount;
    private Long statusId;
    private Long supplierId;
}
