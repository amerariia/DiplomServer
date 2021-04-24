package com.example.springServer.controller;

import com.example.springServer.dto.ChatDto;
import com.example.springServer.dto.UserDto;
import com.example.springServer.dto.UserInfoDto;
import com.example.springServer.mapper.ChatMapper;
import com.example.springServer.mapper.UserInfoMapper;
import com.example.springServer.mapper.UserMapper;
import com.example.springServer.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private UserInfoMapper userMapper;
    @Autowired
    private ChatService chatService;

    @GetMapping("")
    ResponseEntity<List<ChatDto>> getAll(@RequestBody UserInfoDto userDto){
        return new ResponseEntity<>(
                chatService.getAll(userMapper.mapToEntity(userDto)).stream()
                        .map(chatMapper::mapToDomain)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<ChatDto> getById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(
                chatMapper.mapToDomain(chatService.getById(id)),
                HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<ChatDto> add(@RequestBody ChatDto chatDto){
        return new ResponseEntity<>(
                chatMapper.mapToDomain(chatService.add(chatMapper.mapToEntity(chatDto))),
                HttpStatus.OK);
    }

    @PostMapping("edit-chat")
    ResponseEntity<Object> edit(@RequestBody ChatDto chatDto){
        chatService.save(chatMapper.mapToEntity(chatDto));
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}")
    ResponseEntity<Object> deleteById(@PathVariable(name = "id") Integer id){
        chatService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
