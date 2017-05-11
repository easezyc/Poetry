package com.bnu.zhuyongchun.poetry.entity;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public class Poetry {
    private String name,poet,content;
    public Poetry(){}
    public Poetry(String newname,String newpoet,String newcontent){
        name=newname;
        poet=newpoet;
        content=newcontent;
    }
    public String getName(){
        return name;
    }
    public String getPoet(){
        return poet;
    }
    public String getContent(){
        return content;
    }
}
