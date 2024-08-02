package com.example.gastos.gastos.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String token;

    @Column(name = "date_created")
    private Date created;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "account_not_expired")
    private boolean accountNotExpired;
    
    @Column(name = "account_not_locked")
    private boolean accountNotLocked;

    @Column(name = "credential_not_expired")
    private boolean credentialNotExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleModel> roles = new HashSet<>();

    

    public UserModel(String name, String email, String password, String token, Date created, boolean isEnabled,
            boolean accountNotExpired, boolean accountNotLocked, boolean credentialNotExpired, Set<RoleModel> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = token;
        this.created = created;
        this.isEnabled = isEnabled;
        this.accountNotExpired = accountNotExpired;
        this.accountNotLocked = accountNotLocked;
        this.credentialNotExpired = credentialNotExpired;
        this.roles = roles;
    }
}
