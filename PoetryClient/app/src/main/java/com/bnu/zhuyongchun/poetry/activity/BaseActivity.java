package com.bnu.zhuyongchun.poetry.activity;

import android.app.Activity;
import android.os.Bundle;

import com.bnu.zhuyongchun.poetry.presenter.BasePresenter;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public abstract class BaseActivity<T extends BasePresenter> extends Activity {
    protected T basepresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        basepresenter = initPresent();
        onPrepare();
    }

    abstract T initPresent();

    abstract int getLayout();

    abstract void initView();

    abstract void onPrepare();
}