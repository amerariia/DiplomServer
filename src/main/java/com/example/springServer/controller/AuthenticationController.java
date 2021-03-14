package com.example.springServer.controller;

import com.example.springServer.entity.User;
import com.example.springServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    ResponseEntity<User> login(@RequestParam(name = "email") String email,
                               @RequestParam(name = "password") String password){
        userService.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        return new ResponseEntity<>(
                userService.getByEmailAndPassword(email, password),
                HttpStatus.OK);
    }
}
