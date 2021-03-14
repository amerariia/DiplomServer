package com.example.springServer.service;

import com.example.springServer.entity.Message;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface MessageService {
    List<Message> getAll();
    Optional<Message> getById(Integer id);
    void save(Message message);
    void deleteById(Integer id);
    void deleteAll();
}
