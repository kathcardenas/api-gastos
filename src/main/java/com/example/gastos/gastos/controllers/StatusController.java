package com.example.gastos.gastos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gastos.gastos.services.StatusService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping("/status")
    public ResponseEntity<?> getMethod() {
        return ResponseEntity.status(HttpStatus.OK).body(this.statusService.list());
    }

    @GetMapping("/status-costs")
    public ResponseEntity<?> getMethodCosts() {
        List<Long> ids = new ArrayList<>();
        ids.add((long)4); 
        return ResponseEntity.status(HttpStatus.OK).body(this.statusService.FixedCostlist(ids));
    }
    
}
