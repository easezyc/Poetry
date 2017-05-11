package com.bnu.zhuyongchun.poetry.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.entity.Poetry;

import java.util.List;

/**
 * Created by zhuyongchun on 2017/5/11.
 */
public class SearchPoetryAdapter extends BaseAdapter{
    private List<Poetry> mListData;
    private Context mContext;
    public SearchPoetryAdapter(Context context, List<Poetry> listData)
    {
        this.mContext = context;
        this.mListData = listData;
    }
    @Override
    public int getCount()
    {
        return mListData == null ? 0 : mListData.size();
    }

    @Override
    public Poetry getItem(int position)
    {
        return mListData == null ? null : mListData.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        MyViewHolder viewHolder = null;
        if (convertView == null)
        {
            convertView = View.inflate(mContext, R.layout.search_item, null);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(getItem(position).getName());
        viewHolder.poet.setText(getItem(position).getPoet());
        viewHolder.content.setText(getItem(position).getContent());
        return convertView;
    }

    class MyViewHolder
    {
        TextView name;
        TextView poet;
        TextView content;

        public MyViewHolder(View convertView)
        {
            name = (TextView) convertView.findViewById(R.id.poetry_name);
            poet = (TextView) convertView.findViewById(R.id.poet_name);
            content = (TextView) convertView.findViewById(R.id.poetry_content);
        }
    }
}
