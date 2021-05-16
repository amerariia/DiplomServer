package com.example.springServer.mapper.impl;

import com.example.springServer.dto.RoleDto;
import com.example.springServer.dto.UserDto;
import com.example.springServer.entity.Chat;
import com.example.springServer.entity.RoleEntity;
import com.example.springServer.entity.User;
import com.example.springServer.mapper.UserMapper;
import com.example.springServer.repository.ChatRepository;
import com.example.springServer.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public UserDto mapToDomain(User entity) {
        if(entity == null){
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setName(entity.getName());
        userDto.setEmail(entity.getEmail());
        userDto.setPassword(entity.getPassword());
        if(entity.getGroup() == null){
            userDto.setGroupId(null);
        }else {
            userDto.setGroupId(entity.getGroup().getId());
        }
        userDto.setRole(RoleDto.valueOf(entity.getRole().name()));
        userDto.setOwnedChatsIds(entity.getOwnedChats().stream().map(Chat::getId).collect(Collectors.toSet()));
        userDto.setChatsIds(entity.getChats().stream().map(Chat::getId).collect(Collectors.toSet()));

        return userDto;
    }

    @Override
    public User mapToEntity(UserDto domain) {
        if(domain == null){
            return null;
        }

        User user = new User();
        user.setId(domain.getId());
        user.setName(domain.getName());
        user.setEmail(domain.getEmail());
        user.setPassword(domain.getPassword());
        user.setGroup(groupRepository.findById(domain.getGroupId()).orElse(null));
        user.setRole(RoleEntity.valueOf(domain.getRole().name()));
        user.setOwnedChats(domain.getOwnedChatsIds().stream()
                .map(id -> chatRepository.findById(id).orElse(null))
                .collect(Collectors.toSet()));
        user.setChats(domain.getChatsIds().stream()
                .map(id -> chatRepository.findById(id).orElse(null))
                .collect(Collectors.toSet()));

        return user;
    }
}
