package com.example.springServer.controller;

import com.example.springServer.dto.UserDto;
import com.example.springServer.mapper.UserMapper;
import com.example.springServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class AuthenticationController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @PostMapping("login")
    UserDto login(@RequestParam(name = "email") String email,
                                  @RequestParam(name = "password") String password){
        userService.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        return userMapper.mapToDomain(userService.getByEmailAndPassword(email, password));
    }
}
