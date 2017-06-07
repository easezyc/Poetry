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
import android.widget.TextView;
import android.widget.Toast;

import com.bnu.zhuyongchun.poetry.AsyncTask.MyfriendTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.MyverseTask;
import com.bnu.zhuyongchun.poetry.R;

/**
 * Created by zhuyongchun on 2017/5/8.
 */
public class MyfriendFragment extends Fragment {
    private Button back;
    private Button findfriendbutton;
    private TextView textView;
    private FragmentManager fragmentManager;
    private UserFragment userFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View settingLayout = inflater.inflate(R.layout.myfriend,
                container, false);
        back=(Button)settingLayout.findViewById(R.id.backtouser);
        findfriendbutton=(Button)settingLayout.findViewById(R.id.button13);
        textView= (TextView)settingLayout.findViewById(R.id.textView12);
        fragmentManager=getFragmentManager();
        back.setOnClickListener(new View.OnClickListener() {
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

        findfriendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               friend();
            }
        });
         return settingLayout;
    }
    public void friend(){
        MyfriendTask changen=new MyfriendTask(new MyfriendTask.OnLoginedListener() {
            @Override
            public void OnLogined(String result) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        result, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                if(result.equals("")){
                    changefail();
                }
                else{
                    changesuc(result);
                }
            }
        });
        changen.execute();
    }
    public void changefail(){
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "请稍候再试", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    public  void changesuc(String content){
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "检索成功", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        textView.setText(content);

    }



}
