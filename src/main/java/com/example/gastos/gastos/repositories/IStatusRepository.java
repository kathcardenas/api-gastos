package com.example.gastos.gastos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gastos.gastos.models.StatusModel;

public interface IStatusRepository extends JpaRepository<StatusModel, Long>{

    List<StatusModel> findByIdIn(List<Long> id);
}
