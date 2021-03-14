package com.example.springServer.controller;

import com.example.springServer.entity.User;
import com.example.springServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    ResponseEntity<List<User>> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<User> getById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(userService.getById(id).orElse(null), HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<User> add(@RequestBody User user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @PostMapping("edit-user")
    ResponseEntity<Object> edit(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    ResponseEntity<Object> deleteById(@PathVariable(name = "id") Integer id){
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
