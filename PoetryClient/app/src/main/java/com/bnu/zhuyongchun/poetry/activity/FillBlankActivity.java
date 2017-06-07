package com.bnu.zhuyongchun.poetry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bnu.zhuyongchun.poetry.AsyncTask.GetOneFillBlankTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.GetProblemListTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.QueryPoetryTask;
import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.entity.NormalProblem;
import com.bnu.zhuyongchun.poetry.entity.Poetry;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zhuyongchun on 2017/5/30.
 */
public class FillBlankActivity   extends AppCompatActivity implements View.OnClickListener{
    private Button btn_next,btn_look,btn_finish;
    private TextView tv_problem,tv_answer;
    private EditText et_answer;
    private Queue<String> queue;
    private NormalProblem prob;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillblank);
        init();
        GetProblem();
    }
    protected void GetProblem()
    {
        GetProblemListTask task=new GetProblemListTask(new GetProblemListTask.OnLoginedListener(){
            @Override
            public void OnLogined(String result) {
                if(result.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "请检查网络连接", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else{
                    String[] list = result.split(",");
                    for(int i=0;i<list.length;i++)
                    {
                        queue.add(list[i]);
                    }
                }
                GetOneProblem();
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
            String problem=queue.remove();
            GetOneFillBlankTask task=new GetOneFillBlankTask(new GetOneFillBlankTask.OnLoginedListener(){
                @Override
                public void OnLogined(NormalProblem problem) {
                    prob=problem;
                    QueryPoetry(prob.GetPoetryId());
                }
            });
            task.execute(problem);
        }
    }
    protected void QueryPoetry(String pid)
    {
        QueryPoetryTask task=new QueryPoetryTask(new QueryPoetryTask.OnLoginedListener(){
            @Override
            public void OnLogined(Poetry poetry) {
                prob.SetPoetry(poetry);
                prob.handle();
                Ready();
            }
        });
        task.execute(pid);
    }
    protected void Ready()
    {
        et_answer.setText("");
        tv_answer.setText(prob.GetAnswer());
        tv_problem.setText(prob.GetProblem());
        tv_answer.setVisibility(View.INVISIBLE);
    }
    protected void init()
    {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btn_finish=(Button)findViewById(R.id.finish_fillblank);
        btn_next=(Button)findViewById(R.id.next_fillblank);
        btn_look=(Button)findViewById(R.id.look_fillblank);
        tv_answer=(TextView)findViewById(R.id.fillblank_answer);
        tv_problem=(TextView)findViewById(R.id.tv_fillblank);
        et_answer=(EditText)findViewById(R.id.et_fillblank);
        btn_finish.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_look.setOnClickListener(this);
        queue=new LinkedList<String>();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.finish_fillblank:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.next_fillblank:
                if(et_answer.getText().toString().trim().equals(prob.GetAnswer())){
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
            case R.id.look_fillblank:
                tv_answer.setVisibility(View.VISIBLE);
                break;
        }
    }
}
