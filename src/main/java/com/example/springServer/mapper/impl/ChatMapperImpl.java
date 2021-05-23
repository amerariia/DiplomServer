package com.example.springServer.mapper.impl;

import com.example.springServer.dto.ChatDto;
import com.example.springServer.entity.Chat;
import com.example.springServer.mapper.ChatMapper;
import com.example.springServer.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ChatMapperImpl implements ChatMapper {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public ChatDto mapToDomain(Chat entity) {
        if(entity == null){
            return null;
        }

        ChatDto chatDto = new ChatDto();
        chatDto.setId(entity.getId());
        chatDto.setName(entity.getName());
        chatDto.setDescription(entity.getDescription());
        chatDto.setCreator(userInfoMapper.mapToDomain(entity.getCreator()));
        chatDto.setUsers(entity.getUsers().stream()
                .map(userInfoMapper::mapToDomain)
                .collect(Collectors.toSet()));
        return chatDto;
    }

    @Override
    public Chat mapToEntity(ChatDto domain) {
        if(domain == null){
            return null;
        }

        Chat chat = new Chat();
        chat.setId(domain.getId());
        chat.setName(domain.getName());
        chat.setDescription(domain.getDescription());
        chat.setCreator(userInfoMapper.mapToEntity(domain.getCreator()));
        chat.setUsers(domain.getUsers().stream()
                .map(userInfoMapper::mapToEntity)
                .collect(Collectors.toSet()));
        return chat;
    }
}
