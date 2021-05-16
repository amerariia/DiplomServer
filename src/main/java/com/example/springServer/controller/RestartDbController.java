package com.example.springServer.controller;

import com.example.springServer.entity.RoleEntity;
import com.example.springServer.service.ChatService;
import com.example.springServer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/restart-db")
public class RestartDbController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private MessageService messageService;

    @PostMapping("messages")
    void deleteAllMessages(){
        messageService.deleteAll();
    }
    @PostMapping("chats")
    void deleteChats(){
        chatService.deleteAllByCreator_RoleNot(RoleEntity.ADMIN);
    }
}
