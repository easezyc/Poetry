package com.bnu.zhuyongchun.poetry.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.adapter.SearchPoetryAdapter;
import com.bnu.zhuyongchun.poetry.iview.ISearchPoetryView;
import com.bnu.zhuyongchun.poetry.presenter.BasePresenter;
import com.bnu.zhuyongchun.poetry.presenter.SearchPoetryPresenter;

/**
 * Created by zhuyongchun on 2017/5/8.
 */
public class SearchFragment extends BaseFragment<SearchPoetryPresenter> implements ISearchPoetryView{
    private EditText searchContent;
    private Button searchBtn;
    private ListView mListView;
    private SearchPoetryAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    void onPrepare() {
        mAdapter = new SearchPoetryAdapter(getContext(), basepresenter.getAdapterData());
        mListView.setAdapter(mAdapter);
    }

    @Override
    void initView() {
        searchContent=(EditText)mLayout.findViewById(R.id.edit_searchcontent);
        searchBtn=(Button)mLayout.findViewById(R.id.search_btn);
        mListView=(ListView)mLayout.findViewById(R.id.search_listview);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basepresenter.setSearchcontent(searchContent.getText().toString());
                basepresenter.initData();
            }
        });
    }

    @Override
    int getLayout() {
        return R.layout.search_page;
    }

    @Override
    SearchPoetryPresenter initPresenter() {
        return new SearchPoetryPresenter(this);
    }

    @Override
    public void refreshAdapter() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEmpty() {
        mListView.setEmptyView(null);
    }

    @Override
    public void showtoast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }
}
