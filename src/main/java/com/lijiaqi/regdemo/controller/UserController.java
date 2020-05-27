package com.lijiaqi.regdemo.controller;

import com.lijiaqi.regdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.lijiaqi.regdemo.services.UserService;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("adduser")
    @ResponseBody
    public String addUser(String name,String mobile,String email,String password,@RequestParam("file") MultipartFile file){

        String fileName = file.getOriginalFilename();
        String filePath = "/Users/jinsong/IdeaProjects/regdemo/src/main/resources/uploadFiles/";
        String fullPath =  filePath+fileName;
        File dest = new File(fullPath);
        try {
            file.transferTo(dest);
        }catch(Exception e){
            System.out.println(e.toString());
            return "上传文件失败！";
        }
        User user = new User();
        user.setName(name);
        user.setMobile(mobile);
        user.setEmail(email);
        user.setPassword(password);
        user.setLisenceFilePath(fullPath);
        int rows = userService.insert(user);
        return "操作完成，受影响行数为"+rows+"行";

    }


    @GetMapping("userlist")
    public String allUserList(Model model){
        List<User> userlist = userService.getAllUser();
        model.addAttribute("userList",userlist);
        return "userListAll";
    }
}
