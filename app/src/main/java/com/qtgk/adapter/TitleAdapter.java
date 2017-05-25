package com.qtgk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qtgk.bean.NewsClassify;
import com.qtgk.news.R;

import java.util.ArrayList;

public class TitleAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<NewsClassify> list;

    public TitleAdapter(Context mContext, ArrayList<NewsClassify> list) {
        super();
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if(convertView == null){
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.tv_menu, null);
            mViewHolder.mTextView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.mTextView.setText(list.get(position).getName());
        return convertView;
    }

    private class ViewHolder{
        private TextView mTextView;
    }
}
