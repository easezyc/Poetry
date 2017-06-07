package com.bnu.zhuyongchun.poetry.AsyncTask;

import android.os.AsyncTask;

import com.bnu.zhuyongchun.poetry.entity.Poetry;
import com.bnu.zhuyongchun.poetry.entity.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * Created by zhuyongchun on 2017/5/25.
 */
public class GetOnePoetryTask extends AsyncTask<String, Integer, Poetry> {
    public interface OnLoginedListener {
        public void OnLogined(Poetry poetry);
    }

    private OnLoginedListener mListener = null;

    public void setOnLoginedListener(OnLoginedListener listener) {
        this.mListener = listener;
    }

    public GetOnePoetryTask(OnLoginedListener listener) {
        super();
        mListener = listener;
    }

    @Override
    protected void onPostExecute(Poetry poetry) {

        super.onPostExecute(poetry);
        if (mListener != null) {
            mListener.OnLogined(poetry);
        }

    }

    @Override
    protected Poetry doInBackground(String... params) {//两个参数邮箱和密码
        Poetry poetry=new Poetry();
        try{
            Connection.Response res = Jsoup.connect("http://202.112.88.39:8080/PoetryServer/servlet/UserPoetryServlet")
                    .data("uid", User.getUser().getUseremail())
                    .ignoreContentType(true).timeout(1000).method(Connection.Method.POST).execute();
            Gson gson = new Gson();
            JsonObject returnData = new JsonParser().parse(res.body()).getAsJsonObject();
            poetry = gson.fromJson(returnData, Poetry.class);
        }
        catch(Exception e)
        {

        }
        return poetry;
    }
}
