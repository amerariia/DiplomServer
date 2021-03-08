package com.example.springServer.repository;

import com.example.springServer.entity.Chat;
import com.example.springServer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    List<Chat> findAllByUsersContains(User user);
}
