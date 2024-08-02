package com.example.gastos.gastos.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gastos.gastos.dto.DayFixedCostDto;
import com.example.gastos.gastos.models.DailyCostModel;
import com.example.gastos.gastos.models.ProviderModel;
import com.example.gastos.gastos.services.DailyCostService;
import com.example.gastos.gastos.services.ProviderService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class DailyCostController {
    @Autowired
    private DailyCostService dayFixedCostsService;

    @Autowired
    private ProviderService suppliersService;

    @GetMapping("/dayli-fixed-cost")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> getMethod() {
        LocalDate currentDate = LocalDate.now();
        return ResponseEntity.status(HttpStatus.OK).body(this.dayFixedCostsService.listByMonthAndYear(currentDate.getMonthValue(), currentDate.getYear()));
    }

    @GetMapping("/dayli-month-fixed-cost/{month}")
    public ResponseEntity<?> getMethodByDayAndMonth(@PathVariable("month") Integer month) {
        LocalDate currentDate = LocalDate.now();
        return ResponseEntity.status(HttpStatus.OK).body(this.dayFixedCostsService.listByMonthAndYear(month, currentDate.getYear()));
    }
    /*
     * {
        "net": 1001.0,
        "iva": 19.0,
        "total": 119.0,
        "description": "Notebook gammer ñandú",
        "supplierId": 1
    }
     */
    @PostMapping("/dayli-fixed-cost")
    public ResponseEntity<?> postMethod(@RequestBody DayFixedCostDto dto) {
        try {
            ProviderModel supplier = this.suppliersService.findById(dto.getSupplierId());
            if (supplier != null) {
                this.dayFixedCostsService.save(new DailyCostModel(
                    dto.getNet(),
                    dto.getIva(),
                    dto.getTotal(),
                    new Date(),
                    dto.getDescription(),
                    supplier
                ));
                return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>(){
                    {
                        put("message", "Se creó el registro de forma exitosa");
                    }
                });
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Ocurrió un error inesperado");
                }
            });  
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Ocurrió un error inesperado");
                }
            });  
        }
    }
    /*
     * {
        "net": 1001.0,
        "iva": 19.0,
        "total": 119.0,
        "description": "Notebook gammer ñandú",
        "supplierId": 1
    }
     */
    @PutMapping("/dayli-fixed-cost/{id}")
    public ResponseEntity<?> putMethod(@PathVariable("id") Long id, @RequestBody DayFixedCostDto dto) {
        try {
            DailyCostModel data = this.dayFixedCostsService.findById(id);
            ProviderModel provider = this.suppliersService.findById(dto.getSupplierId());
            if (data != null && provider != null) {
                data.setIva(dto.getIva());
                data.setDescription(dto.getDescription());
                data.setNet(dto.getNet());
                data.setTotal(dto.getTotal());
                data.setProviderId(provider);
                this.dayFixedCostsService.save(data);
                return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
                    {
                        put("message", "Se actualizó el registro de forma exitosa");
                    }
                });
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Ocurrió un error inesperado");
                }
            }); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Ocurrió un error inesperado");
                }
            }); 
        }
    }

    @DeleteMapping("/dayli-fixed-cost/{id}")
    public ResponseEntity<?> deleteMethod(@PathVariable("id") Long id){
        try {
            DailyCostModel data = this.dayFixedCostsService.findById(id);
            if (data!=null) {
                this.dayFixedCostsService.delete(id);
                return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
                    {
                        put("message", "Se eliminó el registro de forma exitosa");
                    }
                });
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Ocurrió un error inesperado");
                }
            }); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Ocurrió un error inesperado");
                }
            }); 
        }
    }
}
