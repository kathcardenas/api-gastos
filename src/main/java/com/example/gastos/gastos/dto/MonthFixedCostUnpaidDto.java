package com.example.gastos.gastos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthFixedCostUnpaidDto {
    private Integer num;
    private String name;
    private Double amount;
    private String statusName;
}
