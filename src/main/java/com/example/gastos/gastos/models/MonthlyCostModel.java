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
@Table(name = "monthly_costs")
@Data
public class MonthlyCostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double amount;

    @Column(name = "date_created")
    private Date created;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusModel statusId;

    @ManyToOne()
    @JoinColumn(name = "provider_id")
    private ProviderModel providerId;

    public MonthlyCostModel(String name, Double amount, Date created, StatusModel statusId,
            ProviderModel providerId) {
        this.name = name;
        this.amount = amount;
        this.created = created;
        this.statusId = statusId;
        this.providerId = providerId;
    }

    public MonthlyCostModel() {
    }
    
    
}
