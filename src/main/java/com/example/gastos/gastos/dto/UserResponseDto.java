package com.example.gastos.gastos.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private List<String> roles;
}
