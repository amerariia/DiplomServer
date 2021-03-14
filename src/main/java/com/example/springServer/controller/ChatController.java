package com.example.springServer.controller;

import com.example.springServer.entity.Chat;
import com.example.springServer.entity.User;
import com.example.springServer.service.ChatService;
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
    private ChatService chatService;

    @GetMapping("")
    ResponseEntity<List<Chat>> getAll(@RequestBody User user){
        return new ResponseEntity<>(chatService.getAll(user), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<Chat> getById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(chatService.getById(id).orElse(null), HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<Chat> add(@RequestBody Chat chat){
        return new ResponseEntity<>(chatService.add(chat), HttpStatus.OK);
    }

    @PostMapping("edit-chat")
    ResponseEntity<Object> edit(@RequestBody Chat chat){
        chatService.save(chat);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}")
    ResponseEntity<Object> deleteById(@PathVariable(name = "id") Integer id){
        chatService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
