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

    @GetMapping("create")
    ResponseEntity<User> create(){
        User u1 = new User();
        u1.setEmail("d@d.c");
        u1.setName("aaaaaa");
        u1.setPassword("aaaaa");
        u1.setRole(RoleEntity.STUDENT);
        User u2 = new User();
        u2.setEmail("d@d.c");
        u2.setName("aaaaaa");
        u2.setPassword("pass");
        u2.setRole(RoleEntity.STUDENT);
        User u3 = new User();
        u3.setEmail("d@d.c");
        u3.setName("aaaaaa");
        u3.setPassword("pass");
        u3.setRole(RoleEntity.STUDENT);
        userRepository.saveAll(new ArrayList<User>(){{
            add(u1);
            add(u2);
            add(u3);
        }});
        return ResponseEntity.ok().build();
    }

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
