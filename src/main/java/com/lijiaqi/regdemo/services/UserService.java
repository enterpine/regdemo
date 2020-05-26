package com.lijiaqi.regdemo.services;

import com.lijiaqi.regdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;



@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(User user){
        User rtnUser = new User();

        String sql = "insert into user_info(name,mobile,email,password,lisenceFilePath,passReviewed) values (?,?,?,?,?,?);";
        int rows = jdbcTemplate.update(sql,user.getName(),user.getMobile(),user.getEmail(),user.getPassword(),user.getLisenceFilePath(),user.getPassReviewed());

        return rows;
    }
}
