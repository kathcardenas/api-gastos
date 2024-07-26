package com.example.gastos.gastos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gastos.gastos.models.StatusModel;
import com.example.gastos.gastos.models.UserModel;

public interface IUsersRepository  extends JpaRepository<UserModel, Long>{
    UserModel findByEmail(String email);
    Optional<UserModel> findByEmailAndStatusId(String email, StatusModel status);
}
