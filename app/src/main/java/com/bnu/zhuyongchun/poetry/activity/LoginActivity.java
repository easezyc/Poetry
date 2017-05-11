package com.bnu.zhuyongchun.poetry.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bnu.zhuyongchun.poetry.AsyncTask.LoginTask;
import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.fragment.LoginFragment;

/**
 * Created by zhuyongchun on 2017/5/9.
 */
public class LoginActivity extends Activity{
    private FragmentManager fragmentManager;
    private LoginFragment loginFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(loginFragment==null){
            loginFragment = new LoginFragment();
            transaction.add(R.id.startfragment, loginFragment);
        }
        transaction.show(loginFragment);
        transaction.commit();
    }


}
