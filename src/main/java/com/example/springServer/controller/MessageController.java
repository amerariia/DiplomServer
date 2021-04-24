package com.example.springServer.controller;

import com.example.springServer.dto.MessageDto;
import com.example.springServer.mapper.MessageMapper;
import com.example.springServer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageService messageService;

    @GetMapping("")
    ResponseEntity<List<MessageDto>> getAll(){
        return new ResponseEntity<>(
                messageService.getAll().stream()
                        .map(messageMapper::mapToDomain)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<MessageDto> getById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(
                messageMapper.mapToDomain(messageService.getById(id)),
                HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<MessageDto> add(@RequestBody MessageDto messageDto){
        return new ResponseEntity<>(
                messageMapper.mapToDomain(messageService.save(messageMapper.mapToEntity(messageDto))),
                HttpStatus.OK);
    }

    @PostMapping("{id}")
    ResponseEntity<Object> deleteById(@PathVariable(name = "id") Integer id){
        messageService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
