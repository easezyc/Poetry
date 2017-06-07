package com.bnu.zhuyongchun.poetry.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bnu.zhuyongchun.poetry.AsyncTask.ChangepasswordTask;
import com.bnu.zhuyongchun.poetry.R;

/**
 * Created by zhuyongchun on 2017/5/8.
 */
public class ChangepasswordFragment extends Fragment {
    private Button changepassword;
    private Button back;
    private FragmentManager fragmentManager;
    private UserFragment userFragment;
    private SharedPreferences preferences;
    private EditText editName1;
    private EditText editName2;
    private EditText oldpassword1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View settingLayout = inflater.inflate(R.layout.change_password,
                container, false);
        changepassword=(Button)settingLayout.findViewById(R.id.comfirmpass);
        back=(Button)settingLayout.findViewById(R.id.button15);
        editName1=(EditText) settingLayout.findViewById(R.id.code1);
        editName2=(EditText) settingLayout.findViewById(R.id.code2);
        oldpassword1=(EditText) settingLayout.findViewById(R.id.oldpassword);
        fragmentManager=getFragmentManager();
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changepassword();
            }
        });
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
        return settingLayout;
    }
    public void changepassword(){
        ChangepasswordTask changen=new ChangepasswordTask(new ChangepasswordTask.OnLoginedListener() {
            @Override
            public void OnLogined(String result) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        result, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                if(result.equals("false")){
                    changefail();
                }
                else if(result.equals("wrong")){
                    changewrong();
                }
                else{
                    changesuc();
                }
            }
        });
        changen.execute(editName1.getText().toString(),editName2.getText().toString(),oldpassword1.getText().toString());
    }
    public void changewrong(){
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "确认密码不一致", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    public void changefail(){
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "请稍候再试", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    public  void changesuc(){
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "修改成功", Toast.LENGTH_LONG);
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
