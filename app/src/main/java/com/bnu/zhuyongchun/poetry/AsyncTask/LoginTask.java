package com.bnu.zhuyongchun.poetry.AsyncTask;

import android.os.AsyncTask;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import org.jsoup.Connection;
import org.jsoup.Jsoup;



/**
 * Created by zhuyongchun on 2017/5/9.
 */
public class LoginTask extends AsyncTask<String, Integer, String>{

    public interface OnLoginedListener {
        public void OnLogined(String result);
    }

    private OnLoginedListener mListener = null;

    public void setOnLoginedListener(OnLoginedListener listener) {
        this.mListener = listener;
    }

    public LoginTask(OnLoginedListener listener) {
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
        String email=params[0];
        String password=params[1];
        String name="";
        try{
            Connection.Response res = Jsoup.connect("http://202.112.88.39:8080/test1/servlet/login").data("Email",email).data("Password",password)
                    .timeout(1000).method(Connection.Method.POST).execute();
            JsonObject returnData = new JsonParser().parse(res.body()).getAsJsonObject();
            name=returnData.get("username").toString();
        }
        catch(Exception e)
        {

        }
        return name;
    }
}
