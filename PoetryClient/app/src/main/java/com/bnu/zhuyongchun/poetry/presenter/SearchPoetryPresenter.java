package com.bnu.zhuyongchun.poetry.presenter;

import com.bnu.zhuyongchun.poetry.entity.Poetry;
import com.bnu.zhuyongchun.poetry.fragment.SearchFragment;
import com.bnu.zhuyongchun.poetry.imodel.ISearchPoetryModel;
import com.bnu.zhuyongchun.poetry.interfaces.ValueCallBack;
import com.bnu.zhuyongchun.poetry.ipresentor.ISearchPoetryPresenter;
import com.bnu.zhuyongchun.poetry.iview.ISearchPoetryView;
import com.bnu.zhuyongchun.poetry.model.SearchPoetryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public class SearchPoetryPresenter extends BasePresenter<SearchFragment> implements ISearchPoetryPresenter {
    private ISearchPoetryView mView;
    private ISearchPoetryModel mModel;
    private String searchcontent;
    public SearchPoetryPresenter(ISearchPoetryView view){
        mView=view;
        mModel=new SearchPoetryModel();
    }

    public void setSearchcontent(String scontent){
        searchcontent=scontent;
    }
    @Override
    public List<Poetry> getAdapterData() {
        return mModel.getAdapterData();
    }

    @Override
    public void initData() {
        mModel.getPoetryData(new ValueCallBack<List<Poetry>>() {
            @Override
            public void onSuccess(List<Poetry> poetries) {
                mModel.getAdapterData().clear();
                mModel.getAdapterData().addAll(poetries);
                mView.refreshAdapter();
            }

            @Override
            public void onFail(String code) {
                mView.showtoast(code);
                mView.onEmpty();
            }
        },searchcontent);
    }
}
