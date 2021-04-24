package com.example.springServer.service.impl;

import com.example.springServer.entity.Chat;
import com.example.springServer.entity.Group;
import com.example.springServer.entity.User;
import com.example.springServer.repository.GroupRepository;
import com.example.springServer.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public void changeUsersGroup(Integer fromId, Integer toId) {
        Group groupFrom = groupRepository.findById(fromId).orElseThrow(() -> new RuntimeException(""));
        Group groupTo = groupRepository.findById(toId).orElseThrow(() -> new RuntimeException(""));

        groupFrom.getUsers().forEach(user -> user.setGroup(groupTo));
        groupTo.setUsers(groupFrom.getUsers());
        groupFrom.setUsers(Collections.emptySet());

        groupRepository.save(groupFrom);
        groupRepository.save(groupTo);
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group getById(Integer id) { return groupRepository.findById(id).orElse(null); }

    @Override
    public Group add(Group group) { return groupRepository.save(group); }

    @Override
    public void deleteById(Integer id) {
        groupRepository.deleteById(id);
    }
}
