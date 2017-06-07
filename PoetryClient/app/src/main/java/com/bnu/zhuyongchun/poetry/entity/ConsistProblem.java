package com.bnu.zhuyongchun.poetry.entity;

import java.util.Random;

/**
 * Created by zhuyongchun on 2017/5/31.
 */
public class ConsistProblem  {
    private String sentence1,sentence2;
    private StringBuffer problem;
    public ConsistProblem(String str1,String str2){
        sentence1=str1;
        sentence2=str2;
        problem=new StringBuffer();
        ConsistProblem();
    }
    public ConsistProblem(){

    }
    public String GetSentence1(){
        return sentence1;
    }
    public String GetSentence2(){
        return sentence2;
    }
    public String GetProblem(){
        return problem.toString();
    }
    public void ConsistProblem(){
        problem=new StringBuffer();
        int length=sentence1.length()+sentence2.length();
        char[] sequence = (sentence1+sentence2).toCharArray();
        Random random = new Random();
        for(int i = 0; i < length; i++){
            int p = random.nextInt(length);
            char tmp = sequence[i];
            sequence[i] = sequence[p];
            sequence[p] = tmp;
        }
        for(int i=0;i<length;i++){
            problem.append(sequence[i]);
        }
    }
}
