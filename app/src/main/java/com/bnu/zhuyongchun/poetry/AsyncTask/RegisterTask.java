package com.bnu.zhuyongchun.poetry.AsyncTask;

import android.os.AsyncTask;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public class RegisterTask extends AsyncTask<String, Integer, Boolean>{
    public interface OnLoginedListener {
        public void OnLogined(Boolean result);
    }

    private OnLoginedListener mListener = null;

    public void setOnLoginedListener(OnLoginedListener listener) {
        this.mListener = listener;
    }

    public RegisterTask(OnLoginedListener listener) {
        super();
        mListener = listener;
    }

    @Override
    protected void onPostExecute(Boolean result) {

        super.onPostExecute(result);
        if (mListener != null) {
            mListener.OnLogined(result);
        }

    }

    @Override
    protected Boolean doInBackground(String... params) {//两个参数邮箱和密码
        String email=params[0];
        String password=params[1];
        Boolean result=false;
        try{
            Connection.Response res = Jsoup.connect("http://202.112.88.39:8080/test1/servlet/login").data("Email",email).data("Password",password)
                    .timeout(1000).method(Connection.Method.POST).execute();
            JsonObject returnData = new JsonParser().parse(res.body()).getAsJsonObject();
            //result=returnData.get("result").getAsBoolean();
        }
        catch(Exception e)
        {

        }
        return true;
    }
}
