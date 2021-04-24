package com.example.springServer.dto;

import com.example.springServer.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class ChatDto {
    private Integer id;
    private String name;
    private String description;
    private UserInfoDto creator;
    private Set<UserInfoDto> users;
}
