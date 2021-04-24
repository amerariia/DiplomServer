package com.example.springServer.service.impl;

import com.example.springServer.entity.Message;
import com.example.springServer.repository.MessageRepository;
import com.example.springServer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message getById(Integer id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void deleteById(Integer id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        messageRepository.deleteAll();
    }
}
