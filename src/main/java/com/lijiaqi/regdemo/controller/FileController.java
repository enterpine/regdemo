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

    @RequestMapping("/download/{id}")
    public String download(@PathVariable("id") int id,HttpServletResponse response){
        try {
            String filepath = userService.getUserById(id).getLisenceFilePath();
            File file=new File(filepath);
            // 创建输入流，传入文件对象
            FileInputStream fis =new FileInputStream(file);
            // 设置相关格式
            response.setContentType("application/force-download");
            // 设置下载后的文件名以及header
            response.addHeader("Content-disposition", "attachment;filename=123.jpg");
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
