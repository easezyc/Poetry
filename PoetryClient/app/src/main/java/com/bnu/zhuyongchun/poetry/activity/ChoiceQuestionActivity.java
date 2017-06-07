package com.bnu.zhuyongchun.poetry.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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
 * Created by zhuyongchun on 2017/5/31.
 */
public class ChoiceQuestionActivity extends Activity implements View.OnClickListener {
    private Button btn_submit,btn_look,btn_finish,btn_clear;
    private TextView tv_sentence1,tv_sentence2,tv_sentence3,tv_sentence4,mark1,mark2,mark3,mark4,tv_answer;
    private Queue<String> queue;
    private NormalProblem prob;
    int step;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
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
                    queue.add("3");
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
                prob.split();
                Ready();
            }
        });
        task.execute(pid);
    }
    protected void Ready()
    {
        mark1.setText("");
        mark2.setText("");
        mark3.setText("");
        mark4.setText("");
        tv_sentence1.setText(prob.sentence[prob.sort[0]-1]);
        tv_sentence2.setText(prob.sentence[prob.sort[1]-1]);
        tv_sentence3.setText(prob.sentence[prob.sort[2]-1]);
        tv_sentence4.setText(prob.sentence[prob.sort[3]-1]);
        tv_answer.setText(Integer.toString(prob.sort[0])+Integer.toString(prob.sort[1])+Integer.toString(prob.sort[2])+Integer.toString(prob.sort[3]));
        tv_answer.setVisibility(View.INVISIBLE);
        step=1;
    }
    protected void init()
    {
        btn_clear=(Button)findViewById(R.id.clear_choice);
        btn_finish=(Button)findViewById(R.id.finish_choice);
        btn_look=(Button)findViewById(R.id.check_choice);
        btn_submit=(Button)findViewById(R.id.submit_choice);
        tv_sentence1=(TextView)findViewById(R.id.sentence1);
        tv_sentence2=(TextView)findViewById(R.id.sentence2);
        tv_sentence3=(TextView)findViewById(R.id.sentence3);
        tv_sentence4=(TextView)findViewById(R.id.sentence4);
        mark1=(TextView)findViewById(R.id.mark1);
        mark2=(TextView)findViewById(R.id.mark2);
        mark3=(TextView)findViewById(R.id.mark3);
        mark4=(TextView)findViewById(R.id.mark4);
        tv_answer=(TextView)findViewById(R.id.answer_choice);
        btn_clear.setOnClickListener(this);
        btn_look.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        btn_finish.setOnClickListener(this);
        tv_sentence1.setOnClickListener(this);
        tv_sentence2.setOnClickListener(this);
        tv_sentence3.setOnClickListener(this);
        tv_sentence4.setOnClickListener(this);
        queue=new LinkedList<String>();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.finish_choice:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.submit_choice:
                if(Integer.toString(prob.sort[0]).equals(mark1.getText().toString())||
                        Integer.toString(prob.sort[1]).equals(mark2.getText().toString())||
                        Integer.toString(prob.sort[2]).equals(mark3.getText().toString())||
                        Integer.toString(prob.sort[3]).equals(mark4.getText().toString())){
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
            case R.id.check_choice:
                tv_answer.setVisibility(View.VISIBLE);
                break;
            case R.id.clear_choice:
                mark1.setText("");
                mark2.setText("");
                mark3.setText("");
                mark4.setText("");
                step=1;
                break;
            case R.id.sentence1:
                if(step<=4&&mark1.getText().toString().equals("")) {
                    mark1.setText(Integer.toString(step));
                    step++;
                }
                break;
            case R.id.sentence2:
                if(step<=4&&mark2.getText().toString().equals("")) {
                    mark2.setText(Integer.toString(step));
                    step++;
                }
                break;
            case R.id.sentence3:
                if(step<=4&&mark3.getText().toString().equals("")) {
                    mark3.setText(Integer.toString(step));
                    step++;
                }
                break;
            case R.id.sentence4:
                if(step<=4&&mark4.getText().toString().equals("")) {
                    mark4.setText(Integer.toString(step));
                    step++;
                }
                break;
        }
    }
}
