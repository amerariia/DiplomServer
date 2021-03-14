package com.example.springServer.service.impl;

import com.example.springServer.entity.Group;
import com.example.springServer.repository.GroupRepository;
import com.example.springServer.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
}
