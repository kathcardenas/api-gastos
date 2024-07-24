package com.example.gastos.gastos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gastos.gastos.models.DayFixedCostsModel;

public interface IDayFixedCostsRepository extends JpaRepository<DayFixedCostsModel, Long>{

}
