package com.example.gastos.gastos.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gastos.gastos.dto.SuppliersRequestDto;
import com.example.gastos.gastos.models.SupplierModel;
import com.example.gastos.gastos.services.SuppliersService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class SuppliersController {
    @Autowired
    private SuppliersService suppliersService;

    @GetMapping("/suppliers")
    public ResponseEntity<?> getMethod() {
        return ResponseEntity.status(HttpStatus.OK).body(this.suppliersService.list());
    }

    @GetMapping("/suppliers/{id}")
    public ResponseEntity<?> getMethodById(@PathVariable("id") Long id){
        SupplierModel data = this.suppliersService.findById(id);
        if (data == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message","Recurso no encontrado");
                }
            });
        } else{
            try {
                return ResponseEntity.status(HttpStatus.OK).body(data);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                    {
                        put("message","Recurso no encontrado");
                    }
                });
            }
        }
    }

    @PostMapping("/suppliers")
    public ResponseEntity <?> postMethod(@RequestBody SuppliersRequestDto dto) {
        try {
            this.suppliersService.save(new SupplierModel(dto.getName()));
            return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String,String>(){
                {
                    put("message", "Se creó el registro de manera exitosa");
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

    @PutMapping("/suppliers/{id}")
    public ResponseEntity <?> putMethod(@PathVariable("id") Long id, @RequestBody SuppliersRequestDto dto) {
        SupplierModel data = this.suppliersService.findById(id);
        if (data!=null) {
            try {
                data.setName(dto.getName());
                this.suppliersService.save(data);
                return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
                {
                    put("message","Se actualizó el registro de forma exitosa");
                }
            });
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                    {
                        put("message","Recurso no encontrado");
                    }
                });
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message","Recurso no encontrado");
                }
            });
        }
    }    
}
