package com.example.springServer.controller;

import com.example.springServer.entity.Message;
import com.example.springServer.entity.User;
import com.example.springServer.repository.MessageRepository;
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
    private MessageRepository messageRepository;

    @GetMapping("")
    ResponseEntity<List<Message>> getAll(){
        return new ResponseEntity<>(messageRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<Message> getById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(messageRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<Message> add(@RequestBody Message message){
        return new ResponseEntity<>(messageRepository.save(message), HttpStatus.OK);
    }
}
