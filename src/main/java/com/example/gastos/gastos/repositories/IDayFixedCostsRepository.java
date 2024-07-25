package com.example.gastos.gastos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gastos.gastos.models.DayFixedCostModel;

public interface IDayFixedCostsRepository extends JpaRepository<DayFixedCostModel, Long>{
    @Query(name = "DayFixedCostModel.findAllByMonthAndYear", value="SELECT E FROM DayFixedCostModel E WHERE MONTH(E.created) = :month and YEAR(E.created)=:year order by E.id desc")
    List<DayFixedCostModel> findAllByMonthAndYear(@Param("month") Integer month, @Param("year") Integer year);
}
