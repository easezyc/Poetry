package com.bnu.zhuyongchun.poetry.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bnu.zhuyongchun.poetry.AsyncTask.GetConsistListTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.GetOneFillBlankTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.GetProblemListTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.QueryPoetryTask;
import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.entity.ConsistProblem;
import com.bnu.zhuyongchun.poetry.entity.NormalProblem;
import com.bnu.zhuyongchun.poetry.entity.Poetry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zhuyongchun on 2017/5/31.
 */
public class ConsistProblemActivity extends Activity implements View.OnClickListener {
    private EditText et_answer;
    private Button btn_submit,btn_look,btn_finish;
    private TextView tv_content,tv_answer;
    private Queue<ConsistProblem> queue;
    ConsistProblem nowprob;
    int step;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consist);
        init();
        GetProblem();
    }
    protected void GetProblem()
    {
        GetConsistListTask task=new GetConsistListTask(new GetConsistListTask.OnLoginedListener() {
            @Override
            public void OnLogined(ArrayList<ConsistProblem> problemlist) {
                if(problemlist.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "网络连接失败", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else{
                    for(int i=0;i<problemlist.size();i++){
                        queue.add(problemlist.get(i));
                    }
                    GetOneProblem();
                }
            }
        });
        task.execute();
    }
    protected void GetOneProblem()
    {
        if(queue.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "您已做完本次所有题目", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            nowprob=queue.remove();
            nowprob.ConsistProblem();
            Ready();
        }
    }
    protected void Ready()
    {
        tv_answer.setText(nowprob.GetSentence1());
        tv_answer.setVisibility(View.INVISIBLE);
        tv_content.setText(nowprob.GetProblem());
        et_answer.setText("");
    }
    protected void init()
    {
        btn_finish=(Button)findViewById(R.id.finish_consist);
        btn_look=(Button)findViewById(R.id.check_consist);
        btn_submit=(Button)findViewById(R.id.submit_consist);
        tv_answer=(TextView)findViewById(R.id.consist_answer);
        et_answer=(EditText)findViewById(R.id.et_consit);
        tv_content=(TextView)findViewById(R.id.consist_content);
        btn_finish.setOnClickListener(this);
        btn_look.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        queue=new LinkedList<ConsistProblem>();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.finish_consist:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.submit_consist:
                if(et_answer.getText().toString().equals(nowprob.GetSentence1())){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "回答正确", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    GetOneProblem();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "答案错误", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                break;
            case R.id.check_consist:
                tv_answer.setVisibility(View.VISIBLE);
                break;
        }
    }
}
