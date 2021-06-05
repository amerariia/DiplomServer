package com.example.springServer.mapper.impl;

import com.example.springServer.dto.UserInfoDto;
import com.example.springServer.entity.User;
import com.example.springServer.mapper.UserInfoMapper;
import com.example.springServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserInfoMapperImpl implements UserInfoMapper {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfoDto mapToDomain(User entity) {
        if(Objects.isNull(entity)){
            return null;
        }

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(entity.getId());
        userInfoDto.setName(entity.getName());
        userInfoDto.setEmail(entity.getEmail());
        return userInfoDto;
    }

    @Override
    public User mapToEntity(UserInfoDto domain) {
        if(Objects.isNull(domain)){
            return null;
        }

        return userRepository.findById(domain.getId()).orElse(null);
    }
}
