package com.example.gastos.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.UserModel;
import com.example.gastos.gastos.repositories.IUsersRepository;

@Service
public class UsersService {
@Autowired
    private IUsersRepository repository;

    public List<UserModel> list(){
        return this.repository.findAll(Sort.by("id").descending());
    }

    public void save(UserModel model){
        this.repository.save(model);
    }

    public UserModel findById(Long id){
        Optional<UserModel> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public void delete (Long id){
        this.repository.deleteById(id);
    }
}
