package com.lijiaqi.regdemo.services;

import com.lijiaqi.regdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


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
    public List<User> getAllUser(){
        List<User> userlist = new ArrayList<>();
        String sql="select * from user_info;";
        List result= jdbcTemplate.queryForList(sql);
        Iterator a  =  result.iterator();
        while(a.hasNext()){
            Map m =(Map) a.next();

            User user = new User();
            user.setId(Integer.parseInt(m.get("id").toString()));
            user.setName(m.get("name").toString());
            user.setMobile(m.get("mobile").toString());
            user.setEmail(m.get("email").toString());
            user.setPassword(m.get("password").toString());
            user.setLisenceFilePath(m.get("lisenceFilePath").toString());
            user.setPassReviewed(Boolean.parseBoolean(m.get("passReviewed").toString()));
            userlist.add(user);
        }
        return userlist;
    }

    public User getUserById(int id){
        String sql = "select * from user_info where id ="+id;
        List result= jdbcTemplate.queryForList(sql);
        Iterator a  =  result.iterator();
        User user = new User();
        while(a.hasNext()){
            Map m =(Map) a.next();
            user.setId(Integer.parseInt(m.get("id").toString()));
            user.setName(m.get("name").toString());
            user.setMobile(m.get("mobile").toString());
            user.setEmail(m.get("email").toString());
            user.setPassword(m.get("password").toString());
            user.setLisenceFilePath(m.get("lisenceFilePath").toString());
            user.setPassReviewed(Boolean.parseBoolean(m.get("passReviewed").toString()));
        }
        return user;
    }

    public int verify(User user,int status) {
        int userid = user.getId();
        String passReviewed = status==1?"true":"false";
        String sql = "update user_info set passReviewed = "+passReviewed+" where id = "+ userid;
        int rows = jdbcTemplate.update(sql);
        return rows;
    }
}
