package com.example.gastos.gastos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gastos.gastos.models.UserModel;

public interface IUserRepository  extends JpaRepository<UserModel, Long>{

    /*
     * Método para buscar el usuario por correo
     */
    UserModel findByEmail(String email);

    /*
     * Método para buscar el usuario por correo y el estatus id
     */
    //Optional<UserModel> findByEmailAndRoleId(String email,  RoleModel roleModel);
}
