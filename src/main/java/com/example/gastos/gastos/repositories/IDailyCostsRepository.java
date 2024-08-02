package com.example.gastos.gastos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gastos.gastos.models.DailyCostModel;

public interface IDailyCostsRepository extends JpaRepository<DailyCostModel, Long>{
    /*
     * Método para seleccionar los costos diarios por mes y año
     */
    @Query(name = "DailyCostModel.findAllByMonthAndYear", value="SELECT E FROM DailyCostModel E WHERE MONTH(E.created) = :month and YEAR(E.created)=:year order by E.id desc")
    List<DailyCostModel> findAllByMonthAndYear(@Param("month") Integer month, @Param("year") Integer year);
}
