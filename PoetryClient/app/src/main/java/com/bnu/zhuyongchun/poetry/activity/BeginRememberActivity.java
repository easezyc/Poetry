package com.bnu.zhuyongchun.poetry.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bnu.zhuyongchun.poetry.AsyncTask.ConfirmTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.GetOnePoetryTask;
import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.entity.Poetry;

/**
 * Created by zhuyongchun on 2017/5/25.
 */
public class BeginRememberActivity  extends AppCompatActivity implements View.OnClickListener{
    private TextView poetry_name;
    private TextView poet_name;
    private TextView poetry_content;
    private Button finish;
    private Button nextpoetry;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginremember);
        init();
        GetOnePoetry();
    }

    private void GetOnePoetry()
    {
        GetOnePoetryTask task=new GetOnePoetryTask(new GetOnePoetryTask.OnLoginedListener() {
            @Override
            public void OnLogined(Poetry poetry) {
                if(poetry.getName().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "请检查网络连接", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else{
                    poetry_name.setText(poetry.getName());
                    poet_name.setText(poetry.getPoet());
                    poetry_content.setText(poetry.getContent());
                }
            }
        });
        task.execute();
    }

    private void init()
    {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        poetry_name=(TextView)findViewById(R.id.poetry_name);
        poet_name=(TextView)findViewById(R.id.poet_name);
        poetry_content=(TextView)findViewById(R.id.poetry_content);
        finish=(Button)findViewById(R.id.finish_remember);
        nextpoetry=(Button)findViewById(R.id.btn_nextpoetry);
        finish.setOnClickListener(this);
        nextpoetry.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.finish_remember:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_nextpoetry:
                new AlertDialog.Builder(BeginRememberActivity.this).setTitle("背诵下一首诗")//设置对话框标题
                        .setMessage("请确认当前诗词已经背诵完毕，点击确认将该诗词加入已背")//设置显示的内容
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                ConfirmTask task=new ConfirmTask(new ConfirmTask.OnLoginedListener(){

                                    @Override
                                    public void OnLogined(String result) {
                                        if(result.equals("")){
                                            Toast toast = Toast.makeText(getApplicationContext(),
                                                    "网络问题，确认失败", Toast.LENGTH_LONG);
                                            toast.setGravity(Gravity.CENTER, 0, 0);
                                            toast.show();
                                        }
                                    }
                                });
                                task.execute();
                                GetOnePoetry();
                            }
                        }).setNegativeButton("取消",new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件

                    }
                }).show();
                break;
        }
    }
}
