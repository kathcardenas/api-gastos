package com.example.gastos.gastos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String profile;
    private Long profileId;
    private Long statusId;
    private String status;
}
