package com.example.gastos.gastos.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="gastos_por_dia")
@Data
public class DayFixedCostsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double net;
    private Double iva;
    private Double total;
    private Date date_created_cost;
    
    @Column(length = 65535, columnDefinition = "text")
    private String description;
    
    @ManyToOne()
    @JoinColumn(name = "proveedores_id")
    private SuppliersModel supplierId;

    public DayFixedCostsModel() {
    }

    public DayFixedCostsModel(Double net, Double iva, Double total, Date date_created_cost, String description,
            SuppliersModel supplierId) {
        this.net = net;
        this.iva = iva;
        this.total = total;
        this.date_created_cost = date_created_cost;
        this.description = description;
        this.supplierId = supplierId;
    }

    
}
