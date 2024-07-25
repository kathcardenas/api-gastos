package com.example.gastos.gastos.dto;

import lombok.Data;

@Data
public class DayFixedCostDto {
    private Double net;
    private Double iva;
    private Double total;
    private String description;
    private Long supplierId;

}
