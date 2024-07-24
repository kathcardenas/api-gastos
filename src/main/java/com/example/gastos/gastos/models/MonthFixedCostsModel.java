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
@Table(name="gastos_fijos")
@Data
public class MonthFixedCostsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double amount;
    private Date date_created_cost;
    
    @ManyToOne()
    @JoinColumn(name = "estados_id")
    private StatusModel statusId;

    @ManyToOne()
    @JoinColumn(name = "proveedores_id")
    private SuppliersModel supplierId;

    public MonthFixedCostsModel(String name, Double amount, Date date_created_cost, StatusModel statusId, SuppliersModel supplierId) {
        this.name = name;
        this.amount = amount;
        this.date_created_cost = date_created_cost;
        this.statusId = statusId;
        this.supplierId = supplierId;
    }

    
}
