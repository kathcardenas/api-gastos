package com.example.gastos.gastos.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class UsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    private String token;

    private Date date_created_user;

    @ManyToOne()
    @JoinColumn(name = "perfil_id")
    private ProfilesModel porfileId;

    @ManyToOne()
    @JoinColumn(name = "estados_id")
    private StatusModel statusId;

    public UsersModel(String name, String email, String password, String token, Date date_created_user,
            ProfilesModel porfileId, StatusModel statusId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = token;
        this.date_created_user = date_created_user;
        this.porfileId = porfileId;
        this.statusId = statusId;
    }

    public UsersModel() {
    }
}
