package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.MonthFixedCostsModel;
import com.example.gastos.gastos.repositories.IMonthFixedCostsRepository;

@Service
public class MonthFixedCostsService {
@Autowired
    private IMonthFixedCostsRepository repository;

    public List<MonthFixedCostsModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    public void save(MonthFixedCostsModel model){
        this.repository.save(model);
    }

    public MonthFixedCostsModel findById(Long id){
        Optional<MonthFixedCostsModel> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public void delete (Long id){
        this.repository.deleteById(id);
    }
}
