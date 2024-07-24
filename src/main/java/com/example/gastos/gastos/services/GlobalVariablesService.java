package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.GlobalVariablesModel;
import com.example.gastos.gastos.repositories.IGlobalVariableRepository;

@Service
public class GlobalVariablesService {
@Autowired
    private IGlobalVariableRepository repository;

    public List<GlobalVariablesModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    public void save(GlobalVariablesModel model){
        this.repository.save(model);
    }

    public GlobalVariablesModel findById(Long id){
        Optional<GlobalVariablesModel> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public void delete (Long id){
        this.repository.deleteById(id);
    }
}
