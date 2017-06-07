package com.bnu.zhuyongchun.poetry.entity;

import java.util.Random;

/**
 * Created by zhuyongchun on 2017/5/30.
 */
public class NormalProblem {
    private String problemid,poetryid,answer;
    StringBuffer problem;
    private int start,length;
    private Poetry poetry;
    public String []sentence={"","","",""};
    public int []sort={1,2,3,4};
    public NormalProblem(){
        problem=new StringBuffer();
    }
    public NormalProblem(String proid,String poeid,int star,int len){
        problemid=proid;
        poetryid=poeid;
        start=star;
        length=len;
        problem=new StringBuffer();
    }
    public void SetPoetry(Poetry poetry){
        this.poetry=poetry;
    }
    public Poetry GetPoetry(){
        return poetry;
    }
    public String GetProblemId(){
        return problemid;
    }
    public String GetPoetryId(){
        return poetryid;
    }
    public int GetStart(){
        return start;
    }
    public String GetProblem(){
        return problem.toString();
    }
    public String GetAnswer(){
        return answer;
    }
    public int GetLength(){
        return length;
    }
    public void handle(){
        start-=1;
        answer=poetry.getContent().substring(start,start+length);
        problem.append(poetry.getContent().substring(0,start));
        problem.append("            ");
        problem.append(poetry.getContent().substring(start+length,poetry.getContent().length()));
    }
    public void split(){
        sort[0]=1;
        sort[1]=2;
        sort[2]=3;
        sort[3]=4;
        sentence[0]=poetry.getContent().substring(0,length);
        sentence[1]=poetry.getContent().substring(1+length,1+2*length);
        sentence[2]=poetry.getContent().substring(2+2*length,2+3*length);
        sentence[3]=poetry.getContent().substring(3+3*length,3+4*length);
        int index,t;
        for(int i = 0 ;i < sort.length ;i++){
            index = new Random().nextInt(4)%4;
            t = sort[i];
            sort[i] = sort[index];
            sort[index] = t;
        }
    }
}
