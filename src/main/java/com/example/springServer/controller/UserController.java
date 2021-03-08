package com.example.springServer.controller;

import com.example.springServer.entity.RoleEntity;
import com.example.springServer.entity.User;
import com.example.springServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    ResponseEntity<List<User>> getAll(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<User> getById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(userRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<User> add(@RequestBody User user){
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }
}
