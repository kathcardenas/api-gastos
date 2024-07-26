package com.example.gastos.gastos.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gastos.gastos.dto.LoginDto;
import com.example.gastos.gastos.dto.JwtResponseDto;
import com.example.gastos.gastos.jwt.JwtService;
import com.example.gastos.gastos.models.UserModel;
import com.example.gastos.gastos.services.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsersService usersService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        UserModel user = this.usersService.findByEmailAndActiveStatus(dto.getEmail());
        if (user==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Las credenciales ingresadas no son válidas");
                }
            });
        } else{
            if (this.passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
                String token = this.jwtService.generateToken(user.getEmail());
                return ResponseEntity.status(HttpStatus.OK).body(
                    new JwtResponseDto(
                        user.getId(), 
                        user.getName(), 
                        user.getProfileId().getName(),
                        user.getProfileId().getId(), 
                        token)
                );
                /*return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
                    {
                        put("id", user.getId()+"");
                        put("name", user.getName());
                        put("profile_id", user.getProfileId().getId()+"");
                        put("profile", user.getProfileId().getName());
                        put("token", token);                    
                    }
                });*/
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                    {
                        put("message", "Las credenciales ingresadas no son válidas");
                    }
                });
            }
        }
    }
    
    @GetMapping("/auth/refresh/{id}")
    public ResponseEntity<?> refresh(@PathVariable("id") Long id){
        UserModel user = this.usersService.findById(id);
        try {
            if (user != null) {
                return ResponseEntity.status(HttpStatus.OK).body(
                    new JwtResponseDto(user.getId(), 
                    user.getName(), 
                    user.getProfileId().getName(), 
                    user.getProfileId().getId(), 
                    this.jwtService.generateToken(user.getEmail()))
                );
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message","Ocurrió un error inesperado");
                }
            });
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message","Ocurrió un error inesperado");
                }
            });
        }
    }
}
