package com.example.gastos.gastos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gastos.gastos.dto.MonthFixedCostUnpaidDto;
import com.example.gastos.gastos.models.MonthlyCostModel;

public interface IMonthlyRepository extends JpaRepository<MonthlyCostModel, Long> {
    /*
     * Método para buscar los gastos mensuales por mes y año
     */
    @Query(name = "MonthlyCostModel.findByMonthAndYear", value = "SELECT E FROM MonthlyCostModel E WHERE MONTH(E.created)= :month and YEAR(E.created)= :year order by E.id desc")
    List<MonthlyCostModel> findByMonthAndYear(@Param("month") Integer month,@Param("year")  Integer year);

    /*
     * Método para buscar los gastos mensuales no pagados por mes y año
     */
    @Query("SELECT new com.example.gastos.gastos.dto.MonthFixedCostUnpaidDto(0,mfc.name, mfc.amount, s.name) " +
       "FROM MonthlyCostModel mfc " +
       "JOIN mfc.statusId s " +
       "WHERE s.name = :statusName " +
       "AND MONTH(mfc.created) = :month " +
       "AND YEAR(mfc.created) = :year")
List<MonthFixedCostUnpaidDto> findByStatusNameAndCreatedMonthAndYear(@Param("statusName") String statusName, 
                                                                         @Param("month") Integer month, 
                                                                         @Param("year") Integer year);

}