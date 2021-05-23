package com.example.springServer.controller;

import com.example.springServer.dto.MessageDto;
import com.example.springServer.mapper.MessageMapper;
import com.example.springServer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private MessageService messageService;

    @GetMapping("")
    List<MessageDto> getAll(){
        return messageService.getAll().stream()
                    .map(messageMapper::mapToDomain)
                    .collect(Collectors.toList());
    }

    @GetMapping("chat")
    List<MessageDto> getAllByChatId(@RequestParam("chatId") Integer id){
        return messageService.getAllByChatId(id).stream()
                .map(messageMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    ResponseEntity<MessageDto> getById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(messageMapper.mapToDomain(messageService.getById(id)),
                HttpStatus.OK);
    }

    @PostMapping("")
    MessageDto add(@RequestBody MessageDto messageDto){
        return messageMapper.mapToDomain(messageService.save(messageMapper.mapToEntity(messageDto)));
    }

    @PostMapping("{id}")
    void deleteById(@PathVariable(name = "id") Integer id){
        messageService.deleteById(id);
    }

}
