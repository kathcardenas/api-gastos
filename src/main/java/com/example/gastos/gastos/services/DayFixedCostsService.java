package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.DayFixedCostsModel;
import com.example.gastos.gastos.repositories.IDayFixedCostsRepository;

@Service
public class DayFixedCostsService {
@Autowired
    private IDayFixedCostsRepository repository;

    public List<DayFixedCostsModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    public void save(DayFixedCostsModel model){
        this.repository.save(model);
    }

    public DayFixedCostsModel findById(Long id){
        Optional<DayFixedCostsModel> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public void delete (Long id){
        this.repository.deleteById(id);
    }
}
