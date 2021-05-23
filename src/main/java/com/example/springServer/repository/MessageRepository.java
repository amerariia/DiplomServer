package com.example.springServer.repository;

import com.example.springServer.entity.Message;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByChatId(Integer id);
}
