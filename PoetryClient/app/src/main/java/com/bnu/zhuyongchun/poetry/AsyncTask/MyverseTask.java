package com.bnu.zhuyongchun.poetry.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.bnu.zhuyongchun.poetry.entity.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

import org.jsoup.Connection;
import org.jsoup.Jsoup;


/**
 * Created by zhuyongchun on 2017/5/9.
 */
public class MyverseTask extends AsyncTask<String, Integer, String>{

    public interface OnLoginedListener {
        public void OnLogined(String result);
    }

    private OnLoginedListener mListener = null;

    public void setOnLoginedListener(OnLoginedListener listener) {
        this.mListener = listener;
    }

    public MyverseTask(OnLoginedListener listener) {
        super();
        mListener = listener;
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        if (mListener != null) {
            mListener.OnLogined(s);
        }

    }

    @Override
    protected String doInBackground(String... params) {//两个参数邮箱和密码
        String email= User.getUser().getUseremail();
        String content=new String();
        try{
            Connection.Response res = Jsoup.connect("http://202.112.88.39:8080/PoetryServer/servlet/ShowAllverseSvt").data("uid",email)//修改uid,verse
                .ignoreContentType(true).timeout(1000).method(Connection.Method.POST).execute();

            //JsonObject returnData = new JsonParser().parse(res.body()).getAsJsonObject();

            JsonArray returnarry=new JsonParser().parse(res.body()).getAsJsonArray();

            email="false";
            if(returnarry.size()==0)
                return "noone";
            for(int i=0;i< returnarry.size();i++){
                JsonObject returnData = (JsonObject)returnarry.get(i);
                content+="题目："+returnData.get("name").getAsString()+"\n";
                content+="内容：\n"+returnData.get("content").getAsString()+"\n\n\n\n";
                email="true";
            }
        }
        catch(Exception e) {
            Log.i("aa", e.toString());
        }
        return content;//修改成email
    }
}
