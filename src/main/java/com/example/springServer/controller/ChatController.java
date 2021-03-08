package com.example.springServer.controller;

import com.example.springServer.entity.Chat;
import com.example.springServer.entity.User;
import com.example.springServer.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatRepository chatRepository;

    @GetMapping("")
    ResponseEntity<List<Chat>> getAll(@RequestBody(required = false) User user){
        if(user == null){
            return new ResponseEntity<>(chatRepository.findAll(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(chatRepository.findAllByUsersContains(user), HttpStatus.OK);
        }
    }

    @GetMapping("{id}")
    ResponseEntity<Chat> getById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(chatRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<Chat> add(@RequestBody Chat chat){
        return new ResponseEntity<>(chatRepository.save(chat), HttpStatus.OK);
    }

    @PostMapping("{id}")
    ResponseEntity<Object> deleteById(@PathVariable(name = "id") Integer id){
        chatRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
