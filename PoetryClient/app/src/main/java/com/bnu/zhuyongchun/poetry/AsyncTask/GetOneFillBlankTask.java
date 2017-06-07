package com.bnu.zhuyongchun.poetry.AsyncTask;

import android.os.AsyncTask;

import com.bnu.zhuyongchun.poetry.entity.NormalProblem;
import com.bnu.zhuyongchun.poetry.entity.Poetry;
import com.bnu.zhuyongchun.poetry.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * Created by zhuyongchun on 2017/5/30.
 */
public class GetOneFillBlankTask extends AsyncTask<String, Integer, NormalProblem>{
    public interface OnLoginedListener {
        public void OnLogined(NormalProblem problem);
    }

    private OnLoginedListener mListener = null;

    public void setOnLoginedListener(OnLoginedListener listener) {
        this.mListener = listener;
    }

    public GetOneFillBlankTask(OnLoginedListener listener) {
        super();
        mListener = listener;
    }

    @Override
    protected void onPostExecute(NormalProblem problem) {

        super.onPostExecute(problem);
        if (mListener != null) {
            mListener.OnLogined(problem);
        }

    }

    @Override
    protected NormalProblem doInBackground(String... params) {
        String problemid=params[0];
        NormalProblem problem=new NormalProblem("1","1",6,5);
        try{
            Connection.Response res = Jsoup.connect("http://202.112.88.39:8080/PoetryServer/servlet/FindFillblankServlet")
                    .data("problemid",problemid)
                    .ignoreContentType(true).timeout(1000).method(Connection.Method.POST).execute();
            Gson gson = new Gson();
            JsonObject returnData = new JsonParser().parse(res.body()).getAsJsonObject();
            problem = gson.fromJson(returnData, NormalProblem.class);
        }
        catch(Exception e)
        {

        }
        return problem;
    }
}
