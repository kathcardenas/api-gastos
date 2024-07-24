package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.UsersModel;
import com.example.gastos.gastos.repositories.IUsersRepository;

@Service
public class UsersService {
@Autowired
    private IUsersRepository repository;

    public List<UsersModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    public void save(UsersModel model){
        this.repository.save(model);
    }

    public UsersModel findById(Long id){
        Optional<UsersModel> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public void delete (Long id){
        this.repository.deleteById(id);
    }
}
