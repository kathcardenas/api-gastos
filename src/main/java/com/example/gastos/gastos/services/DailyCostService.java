package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.DailyCostModel;
import com.example.gastos.gastos.repositories.IDailyCostsRepository;

@Service
public class DailyCostService {
@Autowired
    private IDailyCostsRepository repository;

    public List<DailyCostModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    public List<DailyCostModel> listByMonthAndYear(Integer month, Integer year){
        return this.repository.findAllByMonthAndYear(month, year);
    }

    public void save(DailyCostModel model){
        this.repository.save(model);
    }

    public DailyCostModel findById(Long id){
        Optional<DailyCostModel> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public void delete (Long id){
        this.repository.deleteById(id);
    }
}
