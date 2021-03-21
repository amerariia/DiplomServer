package com.example.springServer.controller;

import com.example.springServer.entity.Chat;
import com.example.springServer.entity.Message;
import com.example.springServer.entity.RoleEntity;
import com.example.springServer.service.ChatService;
import com.example.springServer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/restart-db")
public class RestartDbController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private MessageService messageService;

    @PostMapping("messages")
    ResponseEntity<Object> deleteAllMessages(){
        messageService.deleteAll();
        return ResponseEntity.ok().build();
    }
    @PostMapping("chats")
    ResponseEntity<Object> deleteChats(){
        chatService.deleteAllByCreator_RoleNot(RoleEntity.ADMIN);
        return ResponseEntity.ok().build();
    }
}
