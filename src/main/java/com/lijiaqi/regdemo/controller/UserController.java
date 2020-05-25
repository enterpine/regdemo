package com.lijiaqi.regdemo.controller;

import com.lijiaqi.regdemo.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping("adduser")
    public User addUser(String name,int id){
        User user = new User();
        user.setName(name);
        user.setId(id);
        return user;
    }
}
