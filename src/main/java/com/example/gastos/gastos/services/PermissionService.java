package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.PermissionModel;
import com.example.gastos.gastos.repositories.IPermissionRepository;

@Service
public class PermissionService {
    @Autowired
    private IPermissionRepository permissionRepository;

    public List<PermissionModel> list(){
        return this.permissionRepository.findAll(Sort.by("id").descending());
    }

    public void save(PermissionModel model){
        this.permissionRepository.save(model);
    }

    public PermissionModel findById(Long id){
        Optional<PermissionModel> optional = this.permissionRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public void delete (Long id){
        this.permissionRepository.deleteById(id);
    }

}
