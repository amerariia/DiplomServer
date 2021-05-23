package com.example.springServer.service;

import com.example.springServer.dto.MessageDto;
import com.example.springServer.entity.Message;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MessageService {
    List<Message> getAll();
    List<Message> getAllByChatId(Integer id);

    Message getById(Integer id);
    Message save(Message message);
    void deleteById(Integer id);
    void deleteAll();

}
