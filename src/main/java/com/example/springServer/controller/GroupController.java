package com.example.springServer.controller;

import com.example.springServer.entity.Chat;
import com.example.springServer.entity.Group;
import com.example.springServer.entity.User;
import com.example.springServer.service.GroupService;
import com.example.springServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("change-users-group")
    ResponseEntity<Object> edit(@RequestParam(name = "fromId") Integer fromId,
                                @RequestParam(name = "toId") Integer toId){
        groupService.changeUsersGroup(fromId, toId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    ResponseEntity<List<Group>> getAll(){
        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<Group> getById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(groupService.getById(id).orElse(null), HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<Group> add(@RequestBody Group group){
        return new ResponseEntity<>(groupService.add(group), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Object> deleteById(@PathVariable(name = "id") Integer id){
        groupService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
