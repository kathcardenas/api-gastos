package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.ProfilesModel;
import com.example.gastos.gastos.repositories.IProfileRepository;

@Service
public class ProfileService {
@Autowired
    private IProfileRepository repository;

    public List<ProfilesModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    public void save(ProfilesModel model){
        this.repository.save(model);
    }

    public ProfilesModel findById(Long id){
        Optional<ProfilesModel> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public void delete (Long id){
        this.repository.deleteById(id);
    }
}
