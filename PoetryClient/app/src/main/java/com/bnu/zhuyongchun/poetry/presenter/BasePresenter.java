package com.bnu.zhuyongchun.poetry.presenter;

import com.bnu.zhuyongchun.poetry.fragment.BaseFragment;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public abstract class BasePresenter<T extends BaseFragment> {

    abstract void initData();
}
