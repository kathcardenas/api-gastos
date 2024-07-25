package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.ProfileModel;
import com.example.gastos.gastos.repositories.IProfileRepository;

@Service
public class ProfileService {
@Autowired
    private IProfileRepository repository;

    public List<ProfileModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    public void save(ProfileModel model){
        this.repository.save(model);
    }

    public ProfileModel findById(Long id){
        Optional<ProfileModel> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public void delete (Long id){
        this.repository.deleteById(id);
    }
}
