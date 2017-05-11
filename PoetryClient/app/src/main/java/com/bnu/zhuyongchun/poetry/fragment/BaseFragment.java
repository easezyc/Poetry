package com.bnu.zhuyongchun.poetry.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.presenter.BasePresenter;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    protected T basepresenter;
    protected View mLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLayout = inflater.inflate(getLayout(),
                container, false);
        initView();
        basepresenter=initPresenter();
        onPrepare();
        return mLayout;
    }

    abstract T initPresenter();

    abstract int getLayout();

    abstract void initView();

    abstract void onPrepare();
}
