package com.example.springServer.controller;

import com.example.springServer.dto.ChatDto;
import com.example.springServer.dto.UserDto;
import com.example.springServer.dto.UserInfoDto;
import com.example.springServer.mapper.ChatMapper;
import com.example.springServer.mapper.UserInfoMapper;
import com.example.springServer.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private ChatService chatService;

    @GetMapping("")
    List<ChatDto> getAll(@RequestBody UserDto userDto){
        return chatService.getAll(userDto.getChatsIds()).stream()
                        .map(chatMapper::mapToDomain)
                        .collect(Collectors.toList());
    }

    @GetMapping(value = "{id}", produces = "application/json")
    ChatDto getById(@PathVariable(name = "id") Integer id){
        return chatMapper.mapToDomain(chatService.getById(id));
    }

    @PostMapping("")
    ChatDto add(@RequestBody ChatDto chatDto){
        return chatMapper.mapToDomain(chatService.add(chatMapper.mapToEntity(chatDto)));
    }

    @PostMapping("edit-chat")
    void edit(@RequestBody ChatDto chatDto){
        chatService.save(chatMapper.mapToEntity(chatDto));
    }

    @PostMapping("{id}")
    void deleteById(@PathVariable(name = "id") Integer id){
        chatService.deleteById(id);
    }
}
