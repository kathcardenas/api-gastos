package com.example.gastos.gastos.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gastos.gastos.dto.MonthFixedCostDto;
import com.example.gastos.gastos.models.MonthlyCostModel;
import com.example.gastos.gastos.models.StatusModel;
import com.example.gastos.gastos.models.ProviderModel;
import com.example.gastos.gastos.services.MonthlyCostService;
import com.example.gastos.gastos.services.StatusService;
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
public class MonthlyCostController {

    @Autowired
    private MonthlyCostService monthFixedCostsService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ProviderService suppliersService;

    @GetMapping("/fixed-cost")
    public ResponseEntity<?> getMethod() {
        LocalDate currentDate = LocalDate.now();
        return ResponseEntity.status(HttpStatus.OK).body(this.monthFixedCostsService.listByMonthAndYear(currentDate.getMonthValue(), currentDate.getYear())); 
    }

    @GetMapping("/fixed-cost-by-month/{month}")
    public ResponseEntity<?> getMethodByMonth(@PathVariable("month") Integer month) {
        LocalDate currentDate = LocalDate.now();
        return ResponseEntity.status(HttpStatus.OK).body(this.monthFixedCostsService.listByMonthAndYear(month, currentDate.getYear())); 
    }

    @GetMapping("/fixed-cost-not-pay/{month}")
    public ResponseEntity<?> getMethodUnpaidCost(@PathVariable("month") Integer month){
        LocalDate now = LocalDate.now();
        Integer currentYear = now.getYear();

        return ResponseEntity.status(HttpStatus.OK).body(this.monthFixedCostsService.listByStatusNameAndCreatedMonthAndYear("No pagado", month, currentYear)); 
    }

    /*
     * {
        "name":"Cuenta de Cable",
        "amount": 12458.0,
        "supplierId": 3,
        "statusId": 0
        }
     */
    @PostMapping("/fixed-cost")
    public ResponseEntity<?> postMethod(@RequestBody MonthFixedCostDto dto) {
        ProviderModel provider = this.suppliersService.findById(dto.getSupplierId());
        if (provider==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Ocurrió un error inesperado");
                }
            });
        } else {
            try {
                /*
                 * String name, Double amount, Date created, StatusModel statusId,
                    SupplierModel supplierId
                 */
                this.monthFixedCostsService.save(new MonthlyCostModel(
                    dto.getName(),
                    dto.getAmount(),
                    new Date(),
                    this.statusService.findById(4L),
                    provider
                ));
                return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>(){
                    {
                        put("message", "Se creó el registro de forma exitosa");
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
    /*
     * {
        "name":"Cuenta de Cable",
        "amount": 12458.0,
        "supplierId": 3,
        "statusId": 3
        }
     */
    @PutMapping("/fixed-cost/{id}")
    public ResponseEntity<?> putMethod(@PathVariable("id") Long id, @RequestBody MonthFixedCostDto dto) {
        MonthlyCostModel data = this.monthFixedCostsService.findById(id);
        ProviderModel provider = this.suppliersService.findById(dto.getSupplierId());
        StatusModel status = this.statusService.findById(dto.getStatusId());
        if (data == null || provider == null || status == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Ocurrió un error inesperado");
                }
            });
        } else {
            try {
                data.setStatusId(this.statusService.findById(dto.getStatusId()));
                data.setAmount(dto.getAmount());
                data.setName(dto.getName());
                data.setProviderId(provider);
                this.monthFixedCostsService.save(data);
                return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
                    {
                        put("message", "Se actualizó el registro de forma exitosa");
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

    @DeleteMapping("/fixed-cost/{id}")
    public ResponseEntity<?> deleteMethod(@PathVariable("id") Long id){
        MonthlyCostModel data = this.monthFixedCostsService.findById(id);
        if (data == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Ocurrió un error inesperado");
                }
            });  
        } else {
            try {
                this.monthFixedCostsService.delete(id);
                return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
                    {
                        put("message", "Se eliminó el registro de forma exitosa");
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
}
