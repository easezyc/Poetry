package com.bnu.zhuyongchun.poetry.iview;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public interface ISearchPoetryView {
    /**
     * 提示toast
     */
    void showtoast(String msg);
    /**
     * 刷新adapter
     */
    void refreshAdapter();
    void onEmpty();
}
