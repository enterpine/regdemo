package com.lijiaqi.regdemo.entity;

/**
 * @author lijiaqi
 */
public class User {
    private int id;
    private String name;
    private String mobile;
    private String email;
    private String password;
    private String lisenceFilePath;
    private int passReviewed = 0;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){ return this.id; }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    public String getMobile(){
        return this.mobile;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){ return this.password; }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }

    public void setLisenceFilePath(String lisenceFilePath){
        this.lisenceFilePath = lisenceFilePath;
    }
    public String getLisenceFilePath(){
        return this.lisenceFilePath;
    }

    public void setPassReviewed(int isPass){
        this.passReviewed = isPass;
    }
    public int getPassReviewed(){
        return this.passReviewed;
    }

    public String getPassReviewedStaus(){
        String result ="待审核";
        switch(this.passReviewed){
            case 0:
                result = "待审核";
                break;
            case -1:
                result = "未通过";
                break;
            case 1:
                result = "通过";
                break;
        }
        return result;
    }

}

