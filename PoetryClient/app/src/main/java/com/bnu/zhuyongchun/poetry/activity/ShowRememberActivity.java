package com.bnu.zhuyongchun.poetry.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.adapter.SearchPoetryAdapter;
import com.bnu.zhuyongchun.poetry.iview.ISearchPoetryView;
import com.bnu.zhuyongchun.poetry.presenter.ShowRememberPresenter;

/**
 * Created by zhuyongchun on 2017/5/24.
 */
public class ShowRememberActivity extends BaseActivity<ShowRememberPresenter> implements ISearchPoetryView {
    private ListView mListview;
    private SearchPoetryAdapter mAdapter;
    @Override
    ShowRememberPresenter initPresent() {
        return new ShowRememberPresenter(this);
    }

    @Override
    int getLayout() {
        return R.layout.activity_remembered;
    }

    @Override
    void initView() {
        mListview=(ListView)findViewById(R.id.remember_listview);
    }

    @Override
    void onPrepare() {
        mAdapter = new SearchPoetryAdapter(this, basepresenter.getAdapterData());
        mListview.setAdapter(mAdapter);
        basepresenter.initData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void showtoast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void refreshAdapter() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEmpty() {
        mListview.setEmptyView(null);
    }
}
