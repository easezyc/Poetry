package com.bnu.zhuyongchun.poetry.AsyncTask;

import android.os.AsyncTask;

import com.bnu.zhuyongchun.poetry.entity.ConsistProblem;
import com.bnu.zhuyongchun.poetry.entity.NormalProblem;
import com.bnu.zhuyongchun.poetry.entity.Poetry;
import com.bnu.zhuyongchun.poetry.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.ArrayList;

/**
 * Created by zhuyongchun on 2017/5/31.
 */
public class GetConsistListTask extends AsyncTask<String, Integer, ArrayList<ConsistProblem>> {
    public interface OnLoginedListener {
        public void OnLogined(ArrayList<ConsistProblem> problemlist);
    }

    private OnLoginedListener mListener = null;

    public void setOnLoginedListener(OnLoginedListener listener) {
        this.mListener = listener;
    }

    public GetConsistListTask(OnLoginedListener listener) {
        super();
        mListener = listener;
    }

    @Override
    protected void onPostExecute(ArrayList<ConsistProblem> problemlist) {

        super.onPostExecute(problemlist);
        if (mListener != null) {
            mListener.OnLogined(problemlist);
        }

    }

    @Override
    protected ArrayList<ConsistProblem> doInBackground(String... params) {
        ArrayList<ConsistProblem> arraylist=new ArrayList<ConsistProblem>();
        try{
            Connection.Response res = Jsoup.connect("http://202.112.88.39:8080/PoetryServer/servlet/SentenceServlet")
                    .data("uid", User.getUser().getUseremail())
                    .ignoreContentType(true).timeout(1000).method(Connection.Method.POST).execute();
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(res.body()).getAsJsonArray();
            //加强for循环遍历JsonArray
            for (JsonElement p : jsonArray) {
                //使用GSON，直接转成Bean对象
                ConsistProblem problem1 = gson.fromJson(p, ConsistProblem.class);
                arraylist.add(problem1);
            }
        }
        catch(Exception e)
        {

        }
        return arraylist;
    }
}
