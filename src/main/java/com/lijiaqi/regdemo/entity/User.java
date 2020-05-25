package com.lijiaqi.regdemo.entity;

public class User {
    private int id;
    private String name;
    private String mobile;
    private String lisenceFilePath;
    private boolean passReviewed = false;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

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

    public void setLisenceFilePath(String lisenceFilePath){
        this.lisenceFilePath = lisenceFilePath;
    }
    public String getLisenceFilePath(){
        return this.lisenceFilePath;
    }

    public void setPassReviewed(boolean isPass){
        this.passReviewed = isPass;
    }
    public boolean getPassReviewed(){
        return this.passReviewed;
    }



}
