package com.example.gastos.gastos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gastos.gastos.models.ProfilesModel;

public interface IProfileRepository extends JpaRepository<ProfilesModel, Long>{

}
