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

import com.bnu.zhuyongchun.poetry.AsyncTask.ChangenameTask;
import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.entity.User;

/**
 * Created by zhuyongchun on 2017/5/8.
 */
public class ChangenameFragment extends Fragment {
    private Button changename;
    private Button back;
    private FragmentManager fragmentManager;
    private UserFragment userFragment;
    private SharedPreferences preferences;
    private EditText editName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View settingLayout = inflater.inflate(R.layout.change_name,
                container, false);
        changename=(Button)settingLayout.findViewById(R.id.button11);
        back=(Button)settingLayout.findViewById(R.id.button14);
        editName=(EditText) settingLayout.findViewById(R.id.editText);
        fragmentManager=getFragmentManager();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if(userFragment==null){
                    userFragment = new UserFragment();

                }
                transaction.replace(R.id.content, userFragment);
                transaction.commit();
            }
        });
       changename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               changenames();
            }
        });
        return settingLayout;
    }
    void hidethis()
    {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(this);
        transaction.commit();
    }
    public void changenames(){
        ChangenameTask changen=new ChangenameTask(new ChangenameTask.OnLoginedListener() {
            @Override
            public void OnLogined(String result) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        result, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                if(result.equals("false")){
                    changefail();
                }
                else{
                    changesuc();
                    User.getUser().setUsername(editName.getText().toString());
                }
            }
        });
        changen.execute(editName.getText().toString());
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
            transaction.add(R.id.content, userFragment);
        }
        transaction.hide(this);
        transaction.show(userFragment);
        transaction.commit();
    }


}
