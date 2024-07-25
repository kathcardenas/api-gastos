package com.example.gastos.gastos.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "month_fixed_cost")
@Data
public class MonthFixedCostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double amount;
    private Date created;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusModel statusId;

    @ManyToOne()
    @JoinColumn(name = "supplier_id")
    private SupplierModel supplierId;

    public MonthFixedCostModel(String name, Double amount, Date created, StatusModel statusId,
            SupplierModel supplierId) {
        this.name = name;
        this.amount = amount;
        this.created = created;
        this.statusId = statusId;
        this.supplierId = supplierId;
    }

    public MonthFixedCostModel() {
    }
    
    
}
