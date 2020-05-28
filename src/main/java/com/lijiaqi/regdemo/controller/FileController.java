package com.lijiaqi.regdemo.controller;

import com.lijiaqi.regdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.lijiaqi.regdemo.services.UserService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class FileController {


    @Autowired
    private UserService userService;

    @GetMapping("review/download/{id}")
    public String download(@PathVariable("id") int id,HttpServletResponse response){
        try {
            String filepath = userService.getUserById(id).getLisenceFilePath();
            String[] str=filepath.split("/");
            String filename = str[str.length-1];
            File file=new File(filepath);
            FileInputStream fis =new FileInputStream(file);
            response.setContentType("application/force-download");
            response.addHeader("Content-disposition", "attachment;filename="+filename);
            OutputStream os = response.getOutputStream();
            // 常规操作
            byte[] buf = new byte[1024];
            int len = 0;
            while((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            os.close();
            fis.close();
            return "success";       //为了测试方便  我写了两个html  一个是success.html还有一个是error.html  用来表示成功还是失败
        }catch (IOException e){
            e.printStackTrace();
            return "error";
        }
    }
}
