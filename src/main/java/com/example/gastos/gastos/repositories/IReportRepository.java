package com.example.gastos.gastos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.MonthlyCostModel;

@Service
public interface IReportRepository extends JpaRepository<MonthlyCostModel,Long>{

}
