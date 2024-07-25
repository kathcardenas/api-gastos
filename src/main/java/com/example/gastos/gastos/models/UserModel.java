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
@Table(name = "users")
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String password;

    private String token;

    private Date date_created;

    @ManyToOne()
    @JoinColumn(name = "profile_id")
    private ProfileModel profileId;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusModel statusId;

    public UserModel(String name, String email, String password, String token, Date date_created,
            ProfileModel profileId, StatusModel statusId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = token;
        this.date_created = date_created;
        this.profileId = profileId;
        this.statusId = statusId;
    }

    public UserModel() {
    }

}
