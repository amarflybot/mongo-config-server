package com.example.service;

import com.example.domain.Login;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public Boolean login(Login login) {
        return Boolean.TRUE;
    }
}
