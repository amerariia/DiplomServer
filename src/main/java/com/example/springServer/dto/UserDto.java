package com.example.springServer.dto;

import com.example.springServer.entity.RoleEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer groupId;
    private RoleDto role;
    private Set<Integer> ownedChatsIds;
    private Set<Integer> chatsIds;
}
