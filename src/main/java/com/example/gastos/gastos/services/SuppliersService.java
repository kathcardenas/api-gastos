package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.SupplierModel;
import com.example.gastos.gastos.repositories.ISupplierRepository;

@Service
public class SuppliersService {
@Autowired
    private ISupplierRepository repository;

    public List<SupplierModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    public void save(SupplierModel model){
        this.repository.save(model);
    }

    public SupplierModel findById(Long id){
        Optional<SupplierModel> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public void delete (Long id){
        this.repository.deleteById(id);
    }
}
