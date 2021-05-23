package com.example.springServer.service.impl;

import com.example.springServer.entity.Chat;
import com.example.springServer.entity.RoleEntity;
import com.example.springServer.entity.User;
import com.example.springServer.repository.ChatRepository;
import com.example.springServer.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public List<Chat> getAll(User user) {
            return chatRepository.findAllByUsersContains(user);
    }

    @Override
    public Chat getById(Integer id) { return chatRepository.findById(id).orElse(null); }

    @Override
    public Chat add(Chat chat) {
        chat.getUsers().forEach(user -> user.getChats().add(chat));
        //chat.getCreator().getOwnedChats().add(chat);
        if(chat.getCreator().getRole() == RoleEntity.TEACHER){
            chat.getUsers().add(chat.getCreator());
        }
        return chatRepository.save(chat);
    }

    @Override
    public Object save(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public void deleteById(Integer id) {
        chatRepository.deleteById(id);
    }

    @Override
    public void deleteAllByCreator_RoleNot(RoleEntity roleEntity) {

    }
}
