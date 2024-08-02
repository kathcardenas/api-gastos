package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.RoleModel;
import com.example.gastos.gastos.repositories.IRoleRepository;

@Service
public class RoleService {

    @Autowired
    private IRoleRepository roleRepository;

    public List<RoleModel> list(){
        return this.roleRepository.findAll(Sort.by("id").descending());
    }

    public void save(RoleModel model){
        this.roleRepository.save(model);
    }

    public RoleModel findById(Long id){
        Optional<RoleModel> optional = this.roleRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }
    public RoleModel findByName(String name) {
        Optional<RoleModel> role = roleRepository.findByName(name);
        return role.orElse(null);
    }
    /*
    public RoleModel findByName(String name){
        Optional<RoleModel> optional = this.roleRepository.find;
        if(optional.isPresent()){
            return optional.get();
        }

        return null;
    }*/

    public void delete (Long id){
        this.roleRepository.deleteById(id);
    }

}
