package com.example.gastos.gastos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gastos.gastos.models.MonthFixedCostModel;

public interface IMonthFixedCostsRepository extends JpaRepository<MonthFixedCostModel, Long> {
    @Query(name = "MonthFixedCostModel.findByMonthAndYear", value = "SELECT E FROM MonthFixedCostModel E WHERE MONTH(E.created)= :month and YEAR(E.created)= :year order by E.id desc")
    List<MonthFixedCostModel> findByMonthAndYear(@Param("month") Integer month,@Param("year")  Integer year);
}
