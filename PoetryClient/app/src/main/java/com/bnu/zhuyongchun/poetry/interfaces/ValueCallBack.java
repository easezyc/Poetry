package com.bnu.zhuyongchun.poetry.interfaces;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public interface ValueCallBack<T> {
    void onSuccess(T t);

    void onFail(String code);
}
