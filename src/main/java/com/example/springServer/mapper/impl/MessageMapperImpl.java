package com.example.springServer.mapper.impl;

import com.example.springServer.dto.MessageDto;
import com.example.springServer.entity.Message;
import com.example.springServer.mapper.MessageMapper;
import com.example.springServer.mapper.UserInfoMapper;
import com.example.springServer.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageMapperImpl implements MessageMapper {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public MessageDto mapToDomain(Message entity) {
        if(entity == null){
            return null;
        }

        MessageDto messageDto = new MessageDto();
        messageDto.setId(entity.getId());
        messageDto.setMessage(entity.getMessage());
        messageDto.setDate(entity.getDate());
        messageDto.setUserInfo(userInfoMapper.mapToDomain(entity.getUser()));
        messageDto.setChatId(entity.getChat().getId());

        return messageDto;
    }

    @Override
    public Message mapToEntity(MessageDto domain) {
        if(domain == null){
            return null;
        }

        Message message = new Message();
        message.setId(domain.getId());
        message.setMessage(domain.getMessage());
        message.setDate(domain.getDate());
        message.setUser(userInfoMapper.mapToEntity(domain.getUserInfo()));
        message.setChat(chatRepository.findById(domain.getChatId()).orElse(null));

        return message;
    }
}
