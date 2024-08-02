package com.example.gastos.gastos.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gastos.gastos.dto.UserRequestDto;
import com.example.gastos.gastos.dto.UserResponseDto;
import com.example.gastos.gastos.models.RoleModel;
import com.example.gastos.gastos.models.UserModel;
import com.example.gastos.gastos.services.RoleService;
import com.example.gastos.gastos.services.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private UserService usersService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> getMethod() {
         System.out.println("Accessing /users endpoint");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    auth.getAuthorities().forEach(role -> System.out.println("Role: " + role.getAuthority()));
        List<UserResponseDto> list = new ArrayList<>();
        List<UserModel> data = this.usersService.list();
        data.forEach((dato)->{
            List<String> roleNames = dato.getRoles().stream().map(RoleModel::getName)
            .collect(Collectors.toList());
            list.add(new UserResponseDto(
                dato.getId(), 
                dato.getName(), 
                dato.getEmail(), 
                roleNames
                ));
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
            Set<RoleModel> roles = new HashSet<>();

            for (String roleNme : dto.getRoles()){
                RoleModel role = this.roleService.findByName(roleNme);
                if (role != null ){
                    roles.add(role);
                } else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                        {
                            put("message", "El rol no exite");
                        }
                    });
                }
            }

            this.usersService.save(new UserModel(
                dto.getName(),
                dto.getEmail(),
                this.passwordEncoder.encode(dto.getPassword()),
                "",
                new Date(),
                true,
                true,
                true,
                true,
                roles
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
