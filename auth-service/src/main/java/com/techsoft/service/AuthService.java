package com.techsoft.service;

import com.techsoft.entity.UserInfo;
import com.techsoft.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added !!";
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }
    public String generateToken(String userName){
        return jwtService.generateToken(userName);
    }
}
