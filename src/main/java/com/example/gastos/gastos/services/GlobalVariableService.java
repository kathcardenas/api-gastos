package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.GlobalVariableModel;
import com.example.gastos.gastos.repositories.IGlobalVariableRepository;

@Service
public class GlobalVariableService {
@Autowired
    private IGlobalVariableRepository repository;

    public List<GlobalVariableModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    public void save(GlobalVariableModel model){
        this.repository.save(model);
    }

    public GlobalVariableModel findById(Long id){
        Optional<GlobalVariableModel> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public void delete (Long id){
        this.repository.deleteById(id);
    }
}
