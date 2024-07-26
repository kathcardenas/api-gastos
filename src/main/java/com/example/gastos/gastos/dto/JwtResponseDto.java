package com.example.gastos.gastos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponseDto {

    private Long id;
    private String name;
    private String profile;
    private Long profileId;
    private String token;


}
