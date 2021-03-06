package com.example.springServer.service.impl;

import com.example.springServer.entity.Chat;
import com.example.springServer.entity.Group;
import com.example.springServer.entity.User;
import com.example.springServer.repository.GroupRepository;
import com.example.springServer.repository.UserRepository;
import com.example.springServer.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private UserRepository userRepository;
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
    public Group add(Group group) {

        Group savedGroup = groupRepository.save(group);
        group.getUsers().forEach(user -> user.setGroup(savedGroup));
        userRepository.saveAll(group.getUsers());
        return  savedGroup;}

    @Override
    public void deleteById(Integer id) {
        List<User> allByGroup_id = userRepository.findAllByGroup_Id(id);
        allByGroup_id.forEach(user -> user.setGroup(null));
        userRepository.saveAll(allByGroup_id);

        groupRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllByIds(List<Integer> ids) {
        List<User> allByGroup_idIn = userRepository.findAllByGroup_IdIn(ids);
        allByGroup_idIn.forEach(user -> user.setGroup(null));
        userRepository.saveAll(allByGroup_idIn);

        groupRepository.deleteAllByIdIn(ids);
    }
}
