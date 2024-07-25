package com.example.gastos.gastos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gastos.gastos.models.GlobalVariableModel;

public interface IGlobalVariableRepository extends JpaRepository<GlobalVariableModel, Long>{

}
