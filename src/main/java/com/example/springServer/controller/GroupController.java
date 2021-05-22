package com.example.springServer.controller;

import com.example.springServer.dto.GroupDto;
import com.example.springServer.mapper.GroupMapper;
import com.example.springServer.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("group")
public class GroupController {
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private GroupService groupService;

    @PostMapping("change-users-group")
    void edit(@RequestParam(name = "fromId") Integer fromId,
              @RequestParam(name = "toId") Integer toId) {
        groupService.changeUsersGroup(fromId, toId);
    }

    @GetMapping("")
    List<GroupDto> getAll(){
        return groupService.getAll().stream().map(groupMapper::mapToDomain).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    GroupDto getById(@PathVariable(name = "id") Integer id){
        return groupMapper.mapToDomain(groupService.getById(id));
    }

    @PostMapping("")
    GroupDto add(@RequestBody GroupDto groupDto){
        return groupMapper.mapToDomain(groupService.add(groupMapper.mapToEntity(groupDto)));
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable(name = "id") Integer id){
        groupService.deleteById(id);
    }

    @DeleteMapping("")
    void deleteAllByIds(@RequestBody List<Integer> ids){ groupService.deleteAllByIds(ids);}
}
