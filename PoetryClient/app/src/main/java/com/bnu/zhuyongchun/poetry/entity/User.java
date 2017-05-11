package com.bnu.zhuyongchun.poetry.entity;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public class User {
    private String username,userpassword,useremail;
    private volatile static User user;
    private User(){}
    public static User getUser(){
        if(user==null){
            synchronized(User.class){
                if(user==null){
                    user=new User();
                }
            }
        }
        return user;
    }
    public void setUsername(String name){
        username=name;
    }
    public void setPassword(String password){
        userpassword=password;
    }
    public void setUseremail(String email){
        useremail=email;
    }
    public String getUsername(){
        return username;
    }
    public String getUserpassword(){
        return userpassword;
    }
    public String getUseremail(){
        return useremail;
    }
}
