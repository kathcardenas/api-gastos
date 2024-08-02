package com.example.gastos.gastos.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.gastos.gastos.dto.MonthFixedCostUnpaidDto;
import com.example.gastos.gastos.services.MonthlyCostService;
import com.example.gastos.gastos.services.ReportService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private MonthlyCostService monthFixedCostsService;

    @GetMapping("/report-pdf")
    public ResponseEntity<byte[]> getMonthlyFixedCostsUnpaidReport() {
        LocalDate now = LocalDate.now();
        Integer currentYear = now.getYear();
        Integer currentMonth = now.getMonthValue();
        try {
            // Get the data for the report
            List<MonthFixedCostUnpaidDto> data = monthFixedCostsService.listByStatusNameAndCreatedMonthAndYear("No pagado", currentMonth, currentYear);

            // Set report parameters if needed
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ReportTitle", "Gastos Mensuales");

            // Generate the PDF report
            byte[] pdfReport = reportService.generateReport(data, parameters);

            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "GastosMensuales.pdf");

            return new ResponseEntity<>(pdfReport, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
