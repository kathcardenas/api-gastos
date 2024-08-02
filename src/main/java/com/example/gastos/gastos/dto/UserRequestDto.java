package com.example.gastos.gastos.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
    private List<String> roles;

}
