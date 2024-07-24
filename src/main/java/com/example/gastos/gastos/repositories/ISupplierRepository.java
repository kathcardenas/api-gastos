package com.example.gastos.gastos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gastos.gastos.models.SuppliersModel;

public interface ISupplierRepository extends JpaRepository<SuppliersModel, Long>{

}
