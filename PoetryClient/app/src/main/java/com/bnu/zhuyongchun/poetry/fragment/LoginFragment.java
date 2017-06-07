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
import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.activity.MainActivity;
import com.bnu.zhuyongchun.poetry.entity.User;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public class LoginFragment extends Fragment {
    private EditText editPassword;
    private EditText editEmail;
    private Button loginBtn;
    private Switch swichBtn;
    private SharedPreferences preferences;
    private ProgressDialog progressDialog;
    private TextView registerText;
    private TextView forgetPassword;
    private FragmentManager fragmentManager;
    private ForgetFragment forgetFragment;
    private RegisterFragment registerFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_page,
                container, false);
        editPassword=(EditText)view.findViewById(R.id.edit_password);
        editEmail=(EditText)view.findViewById(R.id.edit_email);
        loginBtn=(Button)view.findViewById(R.id.login_btn);
        swichBtn=(Switch)view.findViewById(R.id.switch_remember);
        registerText=(TextView)view.findViewById(R.id.register);
        forgetPassword=(TextView)view.findViewById(R.id.forget_password);
        preferences =
                getActivity().getSharedPreferences(getString(R.string.preference_key),
                        Context.MODE_PRIVATE);
        fragmentManager = getFragmentManager();
        if(preferences.getBoolean("remember", false))
        {
            editEmail.setText(preferences.getString("email", ""));
            editPassword.setText(preferences.getString("password",""));
            swichBtn.setChecked(true);
        }
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        registerText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if(forgetFragment==null){
                    forgetFragment = new ForgetFragment();
                    transaction.replace(R.id.startfragment, forgetFragment);
                }
                transaction.commit();
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if(registerFragment==null){
                    registerFragment = new RegisterFragment();
                    transaction.replace(R.id.startfragment, registerFragment);
                }
                transaction.commit();
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

    public void login() {
        hideKeyboard(getActivity());

        progressDialog = ProgressDialog.show(getActivity(), "登录中", "正在登录...",
                true);
        LoginTask logintask=new LoginTask(new LoginTask.OnLoginedListener() {
            @Override
            public void OnLogined(String result) {
                if(result.equals("")){
                    loginFailed();
                }
                else{
                    loginSuc();
                    User.getUser().setUsername(result);
                }
            }
        });
        logintask.execute(editEmail.getText().toString(),editPassword.getText().toString());
    }
    public void loginFailed(){
        progressDialog.dismiss();
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "请检查网络、邮箱、密码", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    public void loginSuc()
    {
        progressDialog.dismiss();
        if(swichBtn.isChecked()){
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("email", editEmail.getText().toString());
            editor.putString("password", editPassword.getText().toString());
            editor.putBoolean("remember",true);
            editor.apply();
        }
        User.getUser().setPassword(editPassword.getText().toString());
        User.getUser().setUseremail(editEmail.getText().toString());
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}