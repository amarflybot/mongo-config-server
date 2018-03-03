package com.example.web.rest;

import com.example.domain.Login;
import com.example.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Amarendra Kumar
 * This class exposes APIs for Login
 */
@RestController
@RequestMapping("/api")
public class LoginResource {

    private LoginService loginService;

    public LoginResource(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody Login login){

        Boolean status = loginService.login(login);
        login.setHash(login.getUsername());
        return ResponseEntity.ok(login);
    }
}
