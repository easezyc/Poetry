package com.bnu.zhuyongchun.poetry.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bnu.zhuyongchun.poetry.AsyncTask.LoginTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.RegisterTask;
import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.activity.MainActivity;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public class RegisterFragment extends Fragment {
    private EditText editPassword;
    private EditText editEmail;
    private Button registerBtn;
    private ProgressDialog progressDialog;
    private FragmentManager fragmentManager;
    private LoginFragment loginFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_page,
                container, false);
        editPassword=(EditText)view.findViewById(R.id.edit_newpassword);
        editEmail=(EditText)view.findViewById(R.id.edit_newemail);
        registerBtn=(Button)view.findViewById(R.id.register_btn);
        fragmentManager = getFragmentManager();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        return view;
    }
    public static void hideKeyboard(Activity activity) {//隐藏输入法
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void register() {
        hideKeyboard(getActivity());

        progressDialog = ProgressDialog.show(getActivity(), "注册中", "正在注册...",
                true);
        RegisterTask logintask=new RegisterTask(new RegisterTask.OnLoginedListener() {
            @Override
            public void OnLogined(Boolean result) {
                if(!result){
                    registerFailed();
                }
                else{
                    registerSuc();
                }
            }
        });
        logintask.execute(editEmail.getText().toString(),editPassword.getText().toString());
    }
    public void registerFailed(){
        progressDialog.dismiss();
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "网络连接失败，用户已被注册", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    public void registerSuc()
    {
        progressDialog.dismiss();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(loginFragment==null){
            loginFragment = new LoginFragment();
            transaction.replace(R.id.startfragment, loginFragment);
        }
        transaction.commit();
    }
}
