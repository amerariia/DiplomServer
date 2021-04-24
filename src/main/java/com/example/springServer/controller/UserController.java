package com.example.springServer.controller;

import com.example.springServer.dto.UserDto;
import com.example.springServer.entity.User;
import com.example.springServer.mapper.UserInfoMapper;
import com.example.springServer.mapper.UserMapper;
import com.example.springServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("")
    ResponseEntity<List<UserDto>> getAll(){
        return new ResponseEntity<>(
                userService.getAll().stream().map(userMapper::mapToDomain).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<UserDto> getById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(
                userMapper.mapToDomain(userService.getById(id)),
                HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<UserDto> add(@RequestBody UserDto userDto){
        return new ResponseEntity<>(
                userMapper.mapToDomain(userService.save(userMapper.mapToEntity(userDto))),
                HttpStatus.OK);
    }

    @PostMapping("edit-user")
    ResponseEntity<Object> edit(@RequestBody UserDto userDto){
        userService.save(userMapper.mapToEntity(userDto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    ResponseEntity<Object> deleteById(@PathVariable(name = "id") Integer id){
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
