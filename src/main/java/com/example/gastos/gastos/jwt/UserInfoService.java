package com.example.gastos.gastos.jwt;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.UserModel;
import com.example.gastos.gastos.repositories.IUserRepository;

@Service
public class UserInfoService implements UserDetailsService{
    @Autowired
    private IUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userDetail = this.repository.findByEmail(email);
        
        if (userDetail == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userDetail.getRoles().forEach(role -> 
        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName()))));

        return new User(userDetail.getEmail(), 
        userDetail.getPassword(), userDetail.isEnabled(), 
        userDetail.isAccountNotExpired(), 
        userDetail.isCredentialNotExpired(), 
        userDetail.isAccountNotLocked(), authorityList);
    }


}
