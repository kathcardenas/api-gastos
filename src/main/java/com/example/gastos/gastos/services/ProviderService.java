package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.ProviderModel;
import com.example.gastos.gastos.repositories.IProviderRepository;

@Service
public class ProviderService {
@Autowired
    private IProviderRepository repository;

    public List<ProviderModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    public void save(ProviderModel model){
        this.repository.save(model);
    }

    public ProviderModel findById(Long id){
        Optional<ProviderModel> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public void delete (Long id){
        this.repository.deleteById(id);
    }
}
