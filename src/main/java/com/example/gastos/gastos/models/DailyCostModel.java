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
@Table(name = "daily_costs")
@Data
public class DailyCostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double net;
    private Double iva;
    private Double total;

    @Column(name = "date_created")
    private Date created;

    @Column(length = 65535, columnDefinition = "text")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "provider_id")
    private ProviderModel providerId;

    public DailyCostModel(Double net, Double iva, Double total, Date created, String description,
            ProviderModel providerId) {
        this.net = net;
        this.iva = iva;
        this.total = total;
        this.created = created;
        this.description = description;
        this.providerId = providerId;
    }

    public DailyCostModel() {
    }

    
}
