package com.bnu.zhuyongchun.poetry.entity;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public class Poetry {
    private String name,author,content;
    public Poetry(){}
    public Poetry(String newname,String newpoet,String newcontent){
        name=newname;
        author=newpoet;
        content=newcontent;
    }
    public String getName(){
        return name;
    }
    public String getPoet(){
        return author;
    }
    public String getContent(){
        return content;
    }
}
