package com.example.springServer.mapper.impl;

import com.example.springServer.dto.GroupInfoDto;
import com.example.springServer.entity.Group;
import com.example.springServer.mapper.GroupInfoMapper;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupInfoMapperImpl implements GroupInfoMapper {

    @Override
    public GroupInfoDto mapToDomain(Group entity) {
        if(entity == null){
            return null;
        }

        GroupInfoDto group = new GroupInfoDto();
        group.setId(entity.getId());
        group.setName(entity.getName());
        return group;
    }

    @Override
    public Group mapToEntity(GroupInfoDto domain) {
        if(domain == null){
            return null;
        }

        Group group = new Group();
        group.setId(domain.getId());
        group.setName(domain.getName());
        //TODO add users
        return group;
    }
}
