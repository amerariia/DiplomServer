package com.example.springServer.mapper.impl;

import com.example.springServer.dto.GroupDto;
import com.example.springServer.entity.Group;
import com.example.springServer.mapper.GroupMapper;
import com.example.springServer.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GroupMapperImpl implements GroupMapper {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public GroupDto mapToDomain(Group entity) {
        if(entity == null){
            return null;
        }

        GroupDto groupDto = new GroupDto();
        groupDto.setId(entity.getId());
        groupDto.setName(entity.getName());
        groupDto.setUsers(entity.getUsers().stream()
                .map(userInfoMapper::mapToDomain)
                .collect(Collectors.toSet()));

        return groupDto;
    }

    @Override
    public Group mapToEntity(GroupDto domain) {
        if(domain == null){
            return null;
        }

        Group group = new Group();
        group.setId(domain.getId());
        group.setName(domain.getName());
        group.setUsers(domain.getUsers().stream()
                .map(userInfoMapper::mapToEntity)
                .collect(Collectors.toSet()));

        return group;
    }
}
