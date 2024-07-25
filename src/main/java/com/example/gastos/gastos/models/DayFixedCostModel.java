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
@Table(name = "daily_fixed_cost")
@Data
public class DayFixedCostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double net;
    private Double iva;
    private Double total;
    private Date created;

    @Column(length = 65535, columnDefinition = "text")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "supplier_id")
    private SupplierModel supplierId;

    public DayFixedCostModel(Double net, Double iva, Double total, Date created, String description,
            SupplierModel supplierId) {
        this.net = net;
        this.iva = iva;
        this.total = total;
        this.created = created;
        this.description = description;
        this.supplierId = supplierId;
    }

    public DayFixedCostModel() {
    }

    
}
