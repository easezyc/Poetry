package com.bnu.zhuyongchun.poetry.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bnu.zhuyongchun.poetry.AsyncTask.ForgetTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.RegisterTask;
import com.bnu.zhuyongchun.poetry.R;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public class ForgetFragment extends Fragment {
    private EditText editEmail;
    private Button forgetBtn;
    private ProgressDialog progressDialog;
    private FragmentManager fragmentManager;
    private LoginFragment loginFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forget_page,
                container, false);
        editEmail=(EditText)view.findViewById(R.id.user_email);
        forgetBtn=(Button)view.findViewById(R.id.forget_btn);
        fragmentManager = getFragmentManager();
        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
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

    public void submit() {
        hideKeyboard(getActivity());

        progressDialog = ProgressDialog.show(getActivity(), "loading", "正在查询...",
                true);
        ForgetTask forgettask=new ForgetTask(new ForgetTask.OnLoginedListener() {
            @Override
            public void OnLogined(String result) {
                if(result.equals("")){
                    submitFailed();
                }
                else{
                    submitSuc(result);
                }
            }
        });
        forgettask.execute(editEmail.getText().toString());
    }
    public void submitFailed(){
        progressDialog.dismiss();
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "网络连接失败，未找到相应用户", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    public void submitSuc(String result)
    {
        progressDialog.dismiss();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("您的密码为:"+result).
            setPositiveButton("确认",
            new DialogInterface.OnClickListener() {
                // 单击事件
                public void onClick(DialogInterface dialog,
                                    int which) {
                    // 设置TextView文本
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    if(loginFragment==null){
                        loginFragment = new LoginFragment();
                        transaction.replace(R.id.startfragment, loginFragment);
                    }
                    transaction.commit();
                }
            });
        // 创建对话框
        AlertDialog ad = builder.create();
        // 显示对话框
        ad.show();
    }
}
