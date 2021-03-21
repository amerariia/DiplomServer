package com.example.springServer.controller;

import com.example.springServer.entity.Message;
import com.example.springServer.entity.User;
import com.example.springServer.repository.MessageRepository;
import com.example.springServer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("")
    ResponseEntity<List<Message>> getAll(){
        return new ResponseEntity<>(messageService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<Message> getById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(messageService.getById(id).orElse(null), HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<Object> add(@RequestBody Message message){
        messageService.save(message);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}")
    ResponseEntity<Object> deleteById(@PathVariable(name = "id") Integer id){
        messageService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
