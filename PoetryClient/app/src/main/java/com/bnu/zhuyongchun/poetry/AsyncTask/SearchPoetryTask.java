package com.bnu.zhuyongchun.poetry.AsyncTask;

import android.os.AsyncTask;

import com.bnu.zhuyongchun.poetry.entity.Poetry;
import com.bnu.zhuyongchun.poetry.entity.User;
import com.bnu.zhuyongchun.poetry.interfaces.ValueCallBack;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public class SearchPoetryTask extends AsyncTask<String, Integer,List<Poetry>>{
    ValueCallBack<List<Poetry>> callBack;
    public SearchPoetryTask(ValueCallBack<List<Poetry>> callBack) {
        super();
        this.callBack=callBack;
    }

    @Override
    protected void onPostExecute(List<Poetry> poetryList) {
        if(poetryList.isEmpty()){
            callBack.onFail("没找到关键字或您的网络不好");
        }
        else{
            callBack.onSuccess(poetryList);
        }
    }

    @Override
    protected List<Poetry> doInBackground(String... params) {//两个参数邮箱和密码
        String search=params[0];
        Gson gson = new Gson();
        ArrayList<Poetry> poetryArrayList = new ArrayList<>();
        try{
            Connection.Response res = Jsoup.connect("http://202.112.88.39:8080//PoetryServer/servlet/ShowAllpoetrySvt")
                    .data("word",search)
                    .ignoreContentType(true).timeout(1000).method(Connection.Method.POST).execute();
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(res.body()).getAsJsonArray();
            //加强for循环遍历JsonArray
            for (JsonElement p : jsonArray) {
                //使用GSON，直接转成Bean对象
                Poetry poetry = gson.fromJson(p, Poetry.class);
                poetryArrayList.add(poetry);
            }
        }
        catch(Exception e)
        {

        }
        return poetryArrayList;
    }
}
