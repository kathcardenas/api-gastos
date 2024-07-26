package com.example.gastos.gastos.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gastos.gastos.dto.UserRequestDto;
import com.example.gastos.gastos.dto.UserResponseDto;
import com.example.gastos.gastos.models.UserModel;
import com.example.gastos.gastos.services.ProfileService;
import com.example.gastos.gastos.services.StatusService;
import com.example.gastos.gastos.services.UsersService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsersService usersService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ProfileService profileService;

    @GetMapping("/users")
    public ResponseEntity<?> getMethod() {
        List<UserResponseDto> list = new ArrayList<>();
        List<UserModel> data = this.usersService.list();
        data.forEach((dato)->{
            list.add(new UserResponseDto(
                dato.getId(), 
                dato.getName(), 
                dato.getEmail(), 
                dato.getProfileId().getName(), 
                dato.getProfileId().getId(), 
                dato.getStatusId().getId(), 
                dato.getStatusId().getName()));
        });
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    
    /*
     * {
     *  "name": Katherine,
     *  "email": "kath@mail.com",
     *  "password": "12345"
     * }
     */
    @PostMapping("/users")
    public ResponseEntity<?> postMethod(@RequestBody UserRequestDto dto) {
        UserModel user = this.usersService.findByEmail(dto.getEmail());
        if (user==null) {
            this.usersService.save(new UserModel(
                dto.getName(),
                dto.getEmail(),
                this.passwordEncoder.encode(dto.getPassword()),
                "",
                new Date(),
                this.profileService.findById(2L),
                this.statusService.findById(1L)
            ));
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
                {
                    put("message", "Se creó el registro de forma exitosa");
                }
            });
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Ocurrió un error inesperado");
                }
            });
        }
    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity<?> putMethod(@PathVariable("id") Long id,  @RequestBody UserRequestDto dto){
        UserModel user = this.usersService.findById(id);
        if (user!=null) {
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setPassword(this.passwordEncoder.encode(dto.getPassword()));
            this.usersService.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
                {
                    put("message", "Se actualizó el registro de forma exitosa");
                }
            });
            
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Ocurrió un error inesperado");
                }
            });
        }
    }
}
