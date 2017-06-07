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

import com.bnu.zhuyongchun.poetry.AsyncTask.AddfriendTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.ChangenameTask;
import com.bnu.zhuyongchun.poetry.R;

/**
 * Created by zhuyongchun on 2017/5/8.
 */
public class AddnewfriendFragment extends Fragment {
    private Button sub;
    private FragmentManager fragmentManager;
    private UserFragment userFragment;
    private SharedPreferences preferences;
    private EditText editemail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View settingLayout = inflater.inflate(R.layout.addfriend,
                container, false);
        sub=(Button)settingLayout.findViewById(R.id.addnewfriendsbutton);
        editemail=(EditText) settingLayout.findViewById(R.id.newfriend);
        fragmentManager=getFragmentManager();
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addfriend();
            }
        });
        return settingLayout;
    }
    public void addfriend(){
        AddfriendTask changen=new AddfriendTask(new AddfriendTask.OnLoginedListener() {
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
                }
            }
        });
        changen.execute(editemail.getText().toString());
    }
    public void changefail(){
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "添加失败，请稍候再试", Toast.LENGTH_LONG);
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
