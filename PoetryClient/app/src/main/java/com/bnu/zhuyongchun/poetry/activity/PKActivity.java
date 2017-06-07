package com.bnu.zhuyongchun.poetry.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bnu.zhuyongchun.poetry.AsyncTask.CreatePKTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.DeleteTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.GetOneFillBlankTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.GetProblemListTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.GetTimeTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.QueryEmail1Task;
import com.bnu.zhuyongchun.poetry.AsyncTask.QueryEmail2Task;
import com.bnu.zhuyongchun.poetry.AsyncTask.QueryPoetryTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.SetTimeTask;
import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.entity.NormalProblem;
import com.bnu.zhuyongchun.poetry.entity.PK;
import com.bnu.zhuyongchun.poetry.entity.Poetry;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zhuyongchun on 2017/6/1.
 */
public class PKActivity extends Activity implements View.OnClickListener {
    private Button btn_submit,btn_look,btn_finish,btn_clear;
    private TextView tv_sentence1,tv_sentence2,tv_sentence3,tv_sentence4,mark1,mark2,mark3,mark4,tv_answer;
    private Boolean begin;
    private int mark;
    private Queue<String> queue;
    private NormalProblem prob;
    private ProgressDialog progressDialog;
    private long starttime,endtime;
    private int wrongnum;
    private int step;
    private Handler handler=new Handler();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        progressDialog = ProgressDialog.show(this, "匹配中", "等待其他用户...",
                true);
        init();
        GetProblem();
    }
    protected void GetProblem()
    {
        QueryEmail2Task task=new QueryEmail2Task(new QueryEmail2Task.OnLoginedListener() {
            @Override
            public void OnLogined(PK pk) {
                if(pk.GetBacknews().equals("ok")){
                    queue=new LinkedList<String>();
                    QueueReady(pk.GetProblemlist());
                    mark=2;
                    progressDialog.dismiss();
                    starttime=System.currentTimeMillis();
                    GetOneProblem();
                }
                else{
                    CreatePKTask task=new CreatePKTask(new CreatePKTask.OnLoginedListener() {
                        @Override
                        public void OnLogined(PK pk) {
                            if(pk.GetBacknews().equals("ok")){
                                mark=1;
                                starttime=System.currentTimeMillis();
                                QueryEmail1();
                            }
                            else{
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "网络连接失败", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                finishthis();
                            }
                        }
                    });
                    task.execute();
                }

            }
        });
        task.execute();
    }
    Boolean rm=false;
    protected  void QueryEmail1()
    {
        handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //要做的事情
                QueryEmail1Task task=new QueryEmail1Task(new QueryEmail1Task.OnLoginedListener() {
                    @Override
                    public void OnLogined(PK pk) {
                        if(pk.GetBacknews().equals("ok")){
                            queue=new LinkedList<String>();
                            QueueReady(pk.GetProblemlist());
                            starttime=System.currentTimeMillis();
                            GetOneProblem();
                            progressDialog.dismiss();
                            rm=true;
                        }
                    }
                });
                task.execute();
                endtime=System.currentTimeMillis();
                if(endtime-starttime>10000)
                {
                    if(endtime-starttime>10000)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "未匹配到对手，请稍后再试", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        DeleteTask task1=new DeleteTask(new DeleteTask.OnLoginedListener() {
                            @Override
                            public void OnLogined(PK pk) {

                            }
                        });
                        task1.execute(Integer.toString(mark));
                        handler.removeCallbacks(this);
                        rm=true;
                        finishthis();
                    }
                }
                if(rm)
                {
                    return;
                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);
    }
    void GetTime()
    {
        handler=new Handler();
        rm=false;
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //要做的事情
                GetTimeTask task=new GetTimeTask(new GetTimeTask.OnLoginedListener() {
                    @Override
                    public void OnLogined(PK pk) {
                        if(pk.GetBacknews().equals("ok")){
                            progressDialog.dismiss();
                            rm=true;
                            StringBuffer str=new StringBuffer();
                            str.append(pk.GetEmail1()+":耗时"+pk.GetTime1()+"错误数"+pk.GetWrongnum1()+"\n");
                            str.append(pk.GetEmail2()+":耗时"+pk.GetTime2()+"错误数"+pk.GetWrongnum2()+"\n");
                            if(pk.GetWrongnum1()<pk.GetWrongnum2())
                            {
                                str.append("用户"+pk.GetEmail1()+"获胜");
                            }
                            else if(pk.GetWrongnum1()==pk.GetWrongnum2()){
                                if(pk.GetTime1()<pk.GetTime2()){
                                    str.append("用户"+pk.GetEmail1()+"获胜");
                                }
                                else if(pk.GetTime1()==pk.GetTime2()){
                                    str.append("平局");
                                }
                                else{
                                    str.append("用户"+pk.GetEmail2()+"获胜");
                                }
                            }
                            else{
                                str.append("用户"+pk.GetEmail2()+"获胜");
                            }
                            new AlertDialog.Builder(PKActivity.this).setTitle("PK结束")//设置对话框标题
                                    .setMessage(str.toString())//设置显示的内容
                                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                            finishthis();
                                        }
                                    }).show();//在按键响应事件中显示此对话框
                        }
                    }
                });
                task.execute(Integer.toString(mark));
                endtime=System.currentTimeMillis();
                if(endtime-starttime>60000)
                {
                    progressDialog.dismiss();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "对手速度太慢，您获得胜利", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                    DeleteTask task1=new DeleteTask(new DeleteTask.OnLoginedListener() {
                        @Override
                        public void OnLogined(PK pk) {

                        }
                    });
                    task1.execute(Integer.toString(mark));
                    rm=true;
                    finishthis();
                }
                if(!rm)handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);
    }
    protected void QueueReady(String str)
    {
        String[] list = str.split(",");
        for(int i=0;i<list.length;i++)
        {
            queue.add(list[i]);
        }
        begin=true;
    }
    protected void finishthis()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    protected void GetOneProblem()
    {
        if(queue.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "您已做完本次所有题目", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            progressDialog = ProgressDialog.show(this, "loading", "等待对手...",
                    true);
            endtime=System.currentTimeMillis();
            SetTimeTask task=new SetTimeTask(new SetTimeTask.OnLoginedListener() {
                @Override
                public void OnLogined(PK pk) {
                    if(pk.GetBacknews().equals("no")){
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "网络问题", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        finishthis();
                    }
                }
            });
            task.execute(Integer.toString(mark),Long.toString(endtime-starttime),Integer.toString(wrongnum));
            GetTime();
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
        btn_look.setVisibility(View.GONE);
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
        begin=false;
        wrongnum=0;
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
                    wrongnum++;
                    GetOneProblem();
                }
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
