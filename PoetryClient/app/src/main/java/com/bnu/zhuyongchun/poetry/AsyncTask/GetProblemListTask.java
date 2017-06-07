package com.bnu.zhuyongchun.poetry.AsyncTask;

import android.os.AsyncTask;

import com.bnu.zhuyongchun.poetry.entity.NormalProblem;
import com.bnu.zhuyongchun.poetry.entity.Poetry;
import com.bnu.zhuyongchun.poetry.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * Created by zhuyongchun on 2017/5/30.
 */
public class GetProblemListTask extends AsyncTask<String, Integer, String> {

    public interface OnLoginedListener {
        public void OnLogined(String result);
    }

    private OnLoginedListener mListener = null;

    public void setOnLoginedListener(OnLoginedListener listener) {
        this.mListener = listener;
    }

    public GetProblemListTask(OnLoginedListener listener) {
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
        StringBuffer backnews=new StringBuffer();
        try{
            Connection.Response res = Jsoup.connect("http://202.112.88.39:8080/PoetryServer/servlet/FillblankServlet")
                    .data("uid", User.getUser().getUseremail())
                    .ignoreContentType(true).timeout(1000).method(Connection.Method.POST).execute();
            JsonParser parser = new JsonParser();
            Gson gson = new Gson();
            JsonArray jsonArray = parser.parse(res.body()).getAsJsonArray();
            //加强for循环遍历JsonArray
            for (JsonElement p : jsonArray) {
                //使用GSON，直接转成Bean对象
                NormalProblem problem = gson.fromJson(p, NormalProblem.class);
                backnews.append(problem.GetProblemId()+",");
            }
            backnews.deleteCharAt(backnews.length()-1);
        }
        catch(Exception e)
        {

        }
        return backnews.toString();
    }
}
