package com.example.springServer.repository;

import com.example.springServer.entity.Chat;
import com.example.springServer.entity.RoleEntity;
import com.example.springServer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    List<Chat> findAllByUsersContains(User user);
    List<Chat> findAllByIdIn(Set<Integer> ids);
    void deleteAllByCreator_RoleNot(RoleEntity roleEntity);
}
