package com.bnu.zhuyongchun.poetry.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bnu.zhuyongchun.poetry.AsyncTask.GetAllNumTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.GetMyNumTask;
import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.activity.BeginRememberActivity;
import com.bnu.zhuyongchun.poetry.activity.MainActivity;
import com.bnu.zhuyongchun.poetry.activity.ShowRememberActivity;

/**
 * Created by zhuyongchun on 2017/5/8.
 */
public class HomeFragment extends Fragment{
    private Button btn_remember;
    private Button btn_begin;
    private TextView tv_mynum;
    private ProgressBar progress;
    private TextView tv_allnum;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page,
                container, false);
        progress=(ProgressBar)view.findViewById(R.id.progressBar);
        tv_mynum=(TextView)view.findViewById(R.id.done_num);
        btn_remember=(Button)view.findViewById(R.id.btn_showremember);
        btn_begin=(Button)view.findViewById(R.id.btn_beginremember);
        tv_allnum=(TextView)view.findViewById(R.id.textView8);
        btn_remember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowRememberActivity.class);
                startActivity(intent);
            }
        });
        btn_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BeginRememberActivity.class);
                startActivity(intent);
            }
        });
        GetMynum();
        GetAllnum();
        return view;
    }
    private void GetMynum()
    {
        GetMyNumTask task=new GetMyNumTask(new GetMyNumTask.OnLoginedListener() {
            @Override
            public void OnLogined(String result) {
                if(result!=""){
                    progress.setProgress(Integer.parseInt(result));
                    tv_mynum.setText(result);
                }
            }
        });
        task.execute();
    }
    private void GetAllnum()
    {
        GetAllNumTask task=new GetAllNumTask(new GetAllNumTask.OnLoginedListener() {
            @Override
            public void OnLogined(String result) {
                if(result!=""){
                    progress.setMax(Integer.parseInt(result));
                    tv_allnum.setText(result);
                }
            }
        });
        task.execute();
    }
}
