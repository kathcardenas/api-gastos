package com.example.gastos.gastos.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<?> getMethod() {
        return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
            {
                put("estado","OK");
                put("mensaje","Método GET - Api de gastos en marcha");
            }
        });
    }    
}
