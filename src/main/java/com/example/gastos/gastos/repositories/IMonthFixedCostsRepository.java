package com.example.gastos.gastos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gastos.gastos.models.MonthFixedCostsModel;

public interface IMonthFixedCostsRepository extends JpaRepository<MonthFixedCostsModel, Long> {

}
