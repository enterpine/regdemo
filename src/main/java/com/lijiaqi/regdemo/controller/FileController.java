package com.lijiaqi.regdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lijiaqi.regdemo.services.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lijiaqi
 * @version 1.0
 */
@Controller
public class FileController {


    @Autowired
    private UserService userService;

    /**
     *
     * @param id user's id.
     * @param response
     * @return download result.
     */
    @GetMapping("review/download/{id}")
    @ResponseBody
    public String download(@PathVariable("id") int id,HttpServletResponse response){
        try {
            String filepath = userService.getUserById(id).getLisenceFilePath();
            String[] str=filepath.split("/");
            String filename = str[str.length-1].split("_unique_")[0];//文件名去掉唯一表示后缀
            File file=new File(filepath);
            FileInputStream fis =new FileInputStream(file);
            response.setContentType("application/force-download");
            response.addHeader("Content-disposition", "attachment;filename="+filename);
            OutputStream os = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            os.close();
            fis.close();
            return "success";
        }catch (IOException e){
            e.printStackTrace();
            return "error";
        }
    }
}
