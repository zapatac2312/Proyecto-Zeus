/*
package com.demo.controller;


import com.demo.Modelo.Login;
import com.demo.service.LoginService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogingController {

    private LoginService logingService;
    @Autowired
    public LogingController(LoginService logingService) {
        this.logingService = logingService;
    }

    @PostMapping("/login")
    public Boolean login (@RequestBody Login login){
        try {
            return this.logingService.authenticateUser(login);
        } catch (AuthenticationException e) {
            return false;

        }


    }
}

 */




