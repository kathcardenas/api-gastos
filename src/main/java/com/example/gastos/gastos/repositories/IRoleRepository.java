package com.example.gastos.gastos.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gastos.gastos.models.RoleModel;

import java.util.List;
import java.util.Optional;


@Repository
public interface IRoleRepository extends JpaRepository<RoleModel, Long>{

    //List<RoleModel> findByName(List<String> roleNames);

    Optional<RoleModel> findByName(String name);

}
