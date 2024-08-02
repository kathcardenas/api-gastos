package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.dto.MonthFixedCostUnpaidDto;
import com.example.gastos.gastos.models.MonthlyCostModel;
import com.example.gastos.gastos.repositories.IMonthlyRepository;

@Service
public class MonthlyCostService {
@Autowired
    private IMonthlyRepository repository;

    public List<MonthlyCostModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    public List<MonthlyCostModel> listByMonthAndYear(Integer month, Integer year){
        return this.repository.findByMonthAndYear(month, year);
    }

    public List<MonthFixedCostUnpaidDto> listByStatusNameAndCreatedMonthAndYear(String statusName, Integer month, Integer year){
        List<MonthFixedCostUnpaidDto> unpaidCosts = this.repository.findByStatusNameAndCreatedMonthAndYear(statusName, month, year);
        
        // Asignar índices
        IntStream.range(0, unpaidCosts.size()).forEach(i -> unpaidCosts.get(i).setNum(i + 1));
        
        return unpaidCosts;
    }

    
    public void save(MonthlyCostModel model){
        this.repository.save(model);
    }

    public MonthlyCostModel findById(Long id){
        Optional<MonthlyCostModel> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public void delete (Long id){
        this.repository.deleteById(id);
    }
}
