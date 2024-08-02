package com.example.gastos.gastos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gastos.gastos.models.PermissionModel;

public interface IPermissionRepository extends JpaRepository<PermissionModel, Long>{

}
