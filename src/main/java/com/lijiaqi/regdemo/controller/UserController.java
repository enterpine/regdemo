package com.lijiaqi.regdemo.controller;

import com.lijiaqi.regdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.lijiaqi.regdemo.services.UserService;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lijiaqi
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Environment environment;

    /**
     *
     * @param name user's name
     * @param mobile user's mobile_number
     * @param email user's email_address
     * @param password user's password
     * @param file user's lisenceFile
     * @param model
     * @return page "update_down"
     */
    @RequestMapping("adduser")
    public String addUser(String name,String mobile,String email,String password,@RequestParam("file") MultipartFile file,Model model){

        String fileName = file.getOriginalFilename()+"_unique_"+mobile;
        String filePath = environment.getProperty("upload_dictionary");
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
        String message = "操作完成，受影响行数为"+rows+"行";
        model.addAttribute("message",message);
        return "update_done";

    }

    /**
     *
     * @param model
     * @return page "userListAll"
     */
    @GetMapping("userlist")
    public String allUserList(Model model){
        List<User> userlist = userService.getAllUser();
        model.addAttribute("userList",userlist);
        return "userListAll";
    }

    /**
     *
     * @param id user's id
     * @param model
     * @return page "reviewUser"
     */
    @GetMapping("review/{id}")
    public String reviewUser(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        String[] str=user.getLisenceFilePath().split("/");
        model.addAttribute("filename",str[str.length-1].split("_unique_")[0]);
        return "reviewUser";
    }

    /**
     *
     * @param hiddenid user's id hidden in form.
     * @param passReviewed verify result.
     * @param model
     * @return page "update_done"
     */
    @RequestMapping("review/modifyuser")
    public String modifyUser(int hiddenid,int passReviewed,Model model){
        User user = userService.getUserById(hiddenid);
        int rows = userService.verify(user,passReviewed);
        String message = "操作完成，受影响行数为"+rows+"行";
        model.addAttribute("message",message);
        return "update_done";
    }

    /**
     *
     * @param model
     * @return index page
     */
    @RequestMapping("/")
    public String indexpage(Model model){
        return "index";
    }

}
