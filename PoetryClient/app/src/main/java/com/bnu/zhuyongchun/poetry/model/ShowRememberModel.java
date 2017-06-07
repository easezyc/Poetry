package com.bnu.zhuyongchun.poetry.model;

import com.bnu.zhuyongchun.poetry.AsyncTask.SearchPoetryTask;
import com.bnu.zhuyongchun.poetry.AsyncTask.ShowRememberTask;
import com.bnu.zhuyongchun.poetry.entity.Poetry;
import com.bnu.zhuyongchun.poetry.imodel.ISearchPoetryModel;
import com.bnu.zhuyongchun.poetry.interfaces.ValueCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuyongchun on 2017/5/24.
 */
public class ShowRememberModel implements ISearchPoetryModel {
    private List<Poetry> listData;

    public ShowRememberModel() {
        this.listData = new ArrayList<>();
    }
    @Override
    public List<Poetry> getAdapterData() {
        return listData;
    }

    @Override
    public void getPoetryData(ValueCallBack<List<Poetry>> callBack, String searchcontent) {
        ShowRememberTask tTask=new ShowRememberTask(callBack);
        tTask.execute(searchcontent);
    }
}
