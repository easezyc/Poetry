package com.bnu.zhuyongchun.poetry.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.activity.ChoiceQuestionActivity;
import com.bnu.zhuyongchun.poetry.activity.ConsistProblemActivity;
import com.bnu.zhuyongchun.poetry.activity.FillBlankActivity;
import com.bnu.zhuyongchun.poetry.activity.MainActivity;
import com.bnu.zhuyongchun.poetry.activity.PKActivity;
import com.bnu.zhuyongchun.poetry.entity.ConsistProblem;

/**
 * Created by zhuyongchun on 2017/5/8.
 */
public class RememberedFragment extends Fragment{
    private Button btn_fillblank,btn_chooce,btn_consist,btn_pk;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.remembered_page,
                container, false);
        btn_fillblank=(Button) view.findViewById(R.id.btn_fillblank);
        btn_chooce=(Button)view.findViewById(R.id.btn_chooce);
        btn_consist=(Button)view.findViewById(R.id.btn_consist);
        btn_pk=(Button)view.findViewById(R.id.btn_pk);
        btn_pk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PKActivity.class);
                startActivity(intent);
            }
        });
        btn_consist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ConsistProblemActivity.class);
                startActivity(intent);
            }
        });
        btn_fillblank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FillBlankActivity.class);
                startActivity(intent);
            }
        });
        btn_chooce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ChoiceQuestionActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
