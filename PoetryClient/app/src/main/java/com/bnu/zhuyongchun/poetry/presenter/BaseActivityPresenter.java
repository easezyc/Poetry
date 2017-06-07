package com.bnu.zhuyongchun.poetry.presenter;

import com.bnu.zhuyongchun.poetry.activity.BaseActivity;
import com.bnu.zhuyongchun.poetry.fragment.BaseFragment;

/**
 * Created by zhuyongchun on 2017/5/24.
 */
public abstract class BaseActivityPresenter<T extends BaseActivity> {
    abstract void initData();
}
