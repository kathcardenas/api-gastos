package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.DayFixedCostModel;
import com.example.gastos.gastos.repositories.IDayFixedCostsRepository;

@Service
public class DayFixedCostsService {
@Autowired
    private IDayFixedCostsRepository repository;

    public List<DayFixedCostModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    public List<DayFixedCostModel> listByMonthAndYear(Integer month, Integer year){
        return this.repository.findAllByMonthAndYear(month, year);
    }

    public void save(DayFixedCostModel model){
        this.repository.save(model);
    }

    public DayFixedCostModel findById(Long id){
        Optional<DayFixedCostModel> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public void delete (Long id){
        this.repository.deleteById(id);
    }
}
