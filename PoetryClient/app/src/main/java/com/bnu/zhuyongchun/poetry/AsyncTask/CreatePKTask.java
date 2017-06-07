package com.bnu.zhuyongchun.poetry.AsyncTask;

import android.os.AsyncTask;

import com.bnu.zhuyongchun.poetry.entity.PK;
import com.bnu.zhuyongchun.poetry.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * Created by zhuyongchun on 2017/6/1.
 */
public class CreatePKTask extends AsyncTask<String, Integer, PK> {
    public interface OnLoginedListener {
        public void OnLogined(PK pk);
    }

    private OnLoginedListener mListener = null;

    public void setOnLoginedListener(OnLoginedListener listener) {
        this.mListener = listener;
    }

    public CreatePKTask(OnLoginedListener listener) {
        super();
        mListener = listener;
    }

    @Override
    protected void onPostExecute(PK pk) {

        super.onPostExecute(pk);
        if (mListener != null) {
            mListener.OnLogined(pk);
        }

    }

    @Override
    protected PK doInBackground(String... params) {//两个参数邮箱和密码
        PK pk=new PK();
        try{
            Connection.Response res = Jsoup.connect("http://202.112.88.39:8080/PoetryServer/servlet/CreatePKSvt")
                    .data("email", User.getUser().getUseremail())
                    .ignoreContentType(true).timeout(1000).method(Connection.Method.POST).execute();
            Gson gson = new Gson();
            JsonObject returnData = new JsonParser().parse(res.body()).getAsJsonObject();
            pk = gson.fromJson(returnData, PK.class);
        }
        catch(Exception e)
        {

        }
        return pk;
    }
}
