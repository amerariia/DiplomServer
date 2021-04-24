package com.example.springServer.service;

import com.example.springServer.entity.Chat;
import com.example.springServer.entity.Group;
import com.example.springServer.entity.User;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    void changeUsersGroup(Integer fromId, Integer toId);
    List<Group> getAll();
    Group getById(Integer id);
    Group add(Group group);
    void deleteById(Integer id);
}
