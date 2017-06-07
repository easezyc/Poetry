package com.bnu.zhuyongchun.poetry.presenter;

import com.bnu.zhuyongchun.poetry.activity.ShowRememberActivity;
import com.bnu.zhuyongchun.poetry.entity.Poetry;
import com.bnu.zhuyongchun.poetry.entity.User;
import com.bnu.zhuyongchun.poetry.imodel.ISearchPoetryModel;
import com.bnu.zhuyongchun.poetry.interfaces.ValueCallBack;
import com.bnu.zhuyongchun.poetry.ipresentor.IShowRememberPresenter;
import com.bnu.zhuyongchun.poetry.iview.ISearchPoetryView;
import com.bnu.zhuyongchun.poetry.model.SearchPoetryModel;
import com.bnu.zhuyongchun.poetry.model.ShowRememberModel;

import java.util.List;

/**
 * Created by zhuyongchun on 2017/5/24.
 */
public class ShowRememberPresenter extends BaseActivityPresenter<ShowRememberActivity> implements IShowRememberPresenter{
    private ISearchPoetryView mView;
    private ISearchPoetryModel mModel;
    public ShowRememberPresenter(ISearchPoetryView view)
    {
        mView=view;
        mModel=new ShowRememberModel();
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
        }, User.getUser().getUseremail());
    }

    @Override
    public List<Poetry> getAdapterData() {
        return mModel.getAdapterData();
    }
}
