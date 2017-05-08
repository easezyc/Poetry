package com.bnu.zhuyongchun.poetry.com.bnu.zhuyongchu.poetry.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bnu.zhuyongchun.poetry.R;

/**
 * Created by zhuyongchun on 2017/5/8.
 */
public class SearchFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View settingLayout = inflater.inflate(R.layout.search_page,
                container, false);
        return settingLayout;
    }
}
