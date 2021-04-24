package com.example.springServer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class GroupDto {
    private Integer id;
    private String name;
    private Set<UserInfoDto> users;
}
