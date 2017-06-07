package com.bnu.zhuyongchun.poetry.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.entity.User;

/**
 * Created by zhuyongchun on 2017/5/8.
 */
public class UserFragment extends Fragment {
    private Button changename;
    private Button changepassword;
    private Button writeverse;
    private Button myverse;
    private TextView tv_username;
    private FragmentManager fragmentManager;
    private ChangenameFragment changenameFragment1;
    private ChangepasswordFragment changepasswordFragment1;
    private WriteverseFragment writeverseFragment1;
    private MyverseFragment myverseFragment1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View settingLayout = inflater.inflate(R.layout.user_page,
                container, false);
        changename=(Button)settingLayout.findViewById(R.id.button7);
        changepassword=(Button)settingLayout.findViewById(R.id.button8);
        writeverse=(Button)settingLayout.findViewById(R.id.button);
        myverse=(Button)settingLayout.findViewById(R.id.button6);
        tv_username=(TextView)settingLayout.findViewById(R.id.textView2);
        tv_username.setText(User.getUser().getUsername());
        fragmentManager=getFragmentManager();
        changename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if(changenameFragment1==null){
                    changenameFragment1 = new ChangenameFragment();
                }
                transaction.replace(R.id.content,changenameFragment1);
                transaction.commit();
            }
         });
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if(changepasswordFragment1==null){
                    changepasswordFragment1 = new ChangepasswordFragment();

                }
                transaction.replace(R.id.content, changepasswordFragment1);
                transaction.commit();
            }
        });
        writeverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if(writeverseFragment1==null){
                    writeverseFragment1 = new WriteverseFragment();

                }
                transaction.replace(R.id.content, writeverseFragment1);
                transaction.commit();
            }
        });
        myverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if(myverseFragment1==null){
                    myverseFragment1 = new MyverseFragment();

                }
                transaction.replace(R.id.content, myverseFragment1);
                transaction.commit();
            }
        });
         return settingLayout;
    }




}
