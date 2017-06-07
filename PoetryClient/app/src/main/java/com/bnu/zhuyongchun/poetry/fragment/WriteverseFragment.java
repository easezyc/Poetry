package com.bnu.zhuyongchun.poetry.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bnu.zhuyongchun.poetry.AsyncTask.ChangenameTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.WriteverseTask;
import com.bnu.zhuyongchun.poetry.R;

import org.w3c.dom.Text;

/**
 * Created by zhuyongchun on 2017/5/8.
 */
public class WriteverseFragment extends Fragment {
    private Button submit;
    private Button cancle;
    private EditText editTextname;
    private EditText versecontent;
    private TextView textViewtitle;
    private FragmentManager fragmentManager;
    private UserFragment userFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View settingLayout = inflater.inflate(R.layout.writeverse,
                container, false);
        submit=(Button)settingLayout.findViewById(R.id.submitverse);
        cancle=(Button)settingLayout.findViewById(R.id.button12);
        editTextname=(EditText) settingLayout.findViewById(R.id.verse_name);
        versecontent=(EditText) settingLayout.findViewById(R.id.editText4);
        fragmentManager=getFragmentManager();
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if(userFragment==null){
                    userFragment = new UserFragment();
                    transaction.replace(R.id.content, userFragment);
                }
                transaction.commit();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               submitverses();
            }
        });
         return settingLayout;
    }

    public void submitverses(){
        WriteverseTask writeverseTask=new WriteverseTask(new WriteverseTask.OnLoginedListener() {
            @Override
            public void OnLogined(String result) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        result, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                if(result.equals("\"F\"")){
                    changefail();
                }
                else{
                    changesuc();
                }
            }
        });
        writeverseTask.execute(editTextname.getText().toString(),versecontent.getText().toString());
    }
    public void changefail(){
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "请稍候再试", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    public  void changesuc(){
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "添加成功", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(userFragment==null){
            userFragment = new UserFragment();
            transaction.replace(R.id.content, userFragment);
        }
        transaction.commit();
    }




}
