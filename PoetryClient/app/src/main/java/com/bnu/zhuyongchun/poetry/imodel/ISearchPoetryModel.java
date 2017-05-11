package com.bnu.zhuyongchun.poetry.imodel;

import com.bnu.zhuyongchun.poetry.entity.Poetry;
import com.bnu.zhuyongchun.poetry.interfaces.ValueCallBack;
import com.bnu.zhuyongchun.poetry.model.SearchPoetryModel;

import java.util.List;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public interface ISearchPoetryModel {
    /**
     * 获取数据
     */
    void getPoetryData(ValueCallBack<List<Poetry>> callBack,String searchcontent);


    /**
     * 返回本地adapter数据
     * @return
     */
    List<Poetry> getAdapterData();
}
