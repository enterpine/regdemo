package com.lijiaqi.regdemo.controller;

import com.lijiaqi.regdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.lijiaqi.regdemo.services.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("adduser")
    @ResponseBody
    public String addUser(String name,String mobile,String email,String password,String lisenceFilePath){
            User user = new User();
            user.setName(name);
            user.setMobile(mobile);
            user.setEmail(email);
            user.setPassword(password);
            user.setLisenceFilePath(lisenceFilePath);
            int rows = userService.insert(user);
            return "操作完成，受影响行数为"+rows+"行";
    }
}
