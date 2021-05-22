package com.example.springServer.dto;

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
    private GroupInfoDto group;
    private RoleDto role;
    private Set<Integer> ownedChatsIds;
    private Set<Integer> chatsIds;
}
