package com.example.springServer.controller;

import com.example.springServer.dto.GroupDto;
import com.example.springServer.entity.Group;
import com.example.springServer.mapper.GroupMapper;
import com.example.springServer.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("group")
public class GroupController {
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private GroupService groupService;

    @PostMapping("change-users-group")
    ResponseEntity<Object> edit(@RequestParam(name = "fromId") Integer fromId,
                                @RequestParam(name = "toId") Integer toId){
        groupService.changeUsersGroup(fromId, toId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    ResponseEntity<List<GroupDto>> getAll(){
        return new ResponseEntity<>(
                groupService.getAll().stream().map(groupMapper::mapToDomain).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<GroupDto> getById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(
                groupMapper.mapToDomain(groupService.getById(id)),
                HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<GroupDto> add(@RequestBody GroupDto groupDto){
        return new ResponseEntity<>(
                groupMapper.mapToDomain(groupService.add(groupMapper.mapToEntity(groupDto))),
                HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Object> deleteById(@PathVariable(name = "id") Integer id){
        groupService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
