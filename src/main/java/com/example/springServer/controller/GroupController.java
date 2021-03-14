package com.example.springServer.controller;

import com.example.springServer.service.GroupService;
import com.example.springServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
