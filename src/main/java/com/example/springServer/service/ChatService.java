package com.example.springServer.service;

import com.example.springServer.entity.Chat;
import com.example.springServer.entity.RoleEntity;
import com.example.springServer.entity.User;

import java.util.List;
import java.util.Optional;

public interface ChatService {
    List<Chat> getAll(User user);
    Chat getById(Integer id);
    Chat add(Chat chat);
    Object save(Chat chat);
    void deleteById(Integer id);
    void deleteAllByCreator_RoleNot(RoleEntity roleEntity);
}
