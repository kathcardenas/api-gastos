package com.example.gastos.gastos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gastos.gastos.models.UserModel;

public interface IUsersRepository  extends JpaRepository<UserModel, Long>{

}
