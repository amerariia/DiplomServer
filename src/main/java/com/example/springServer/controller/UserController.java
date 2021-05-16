package com.example.springServer.controller;

import com.example.springServer.dto.UserDto;
import com.example.springServer.mapper.UserMapper;
import com.example.springServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("")
    List<UserDto> getAll(){
        return userService.getAll().stream().map(userMapper::mapToDomain).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    UserDto getById(@PathVariable(name = "id") Integer id){
        return userMapper.mapToDomain(userService.getById(id));
    }

    @PostMapping("")
    UserDto add(@RequestBody UserDto userDto){
        return userMapper.mapToDomain(userService.save(userMapper.mapToEntity(userDto)));
    }

    @PostMapping("edit-user")
    void edit(@RequestBody UserDto userDto){
        userService.save(userMapper.mapToEntity(userDto));
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable(name = "id") Integer id){
        userService.deleteById(id);
    }
}
