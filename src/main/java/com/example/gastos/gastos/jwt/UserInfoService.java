package com.example.gastos.gastos.jwt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.gastos.gastos.models.UserModel;
import com.example.gastos.gastos.repositories.IUsersRepository;
import com.example.gastos.gastos.services.StatusService;

@Service
public class UserInfoService implements UserDetailsService{
    @Autowired
    private IUsersRepository repository;

    @Autowired
    private StatusService statusService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userDetail = this.repository.findByEmailAndStatusId(username, this.statusService.findById(1L));
        return userDetail.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"+username));
    }


}
