package com.qtgk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qtgk.bean.NewsInfor;
import com.qtgk.news.R;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<NewsInfor> list;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public NewsAdapter(Context mContext, ArrayList<NewsInfor> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_of_newslist, viewGroup, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        NewsInfor mNewsInfor = list.get(i);
        Glide.with(mContext).load(mNewsInfor.getThumbnail_pic_s()).
                error(R.mipmap.ic_launcher_round).into(viewHolder.mImageView);
        viewHolder.tv_title.setText(mNewsInfor.getTitle());
        viewHolder.tv_time.setText(mNewsInfor.getData());
        viewHolder.itemView.setTag(i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView tv_title;
        TextView tv_time;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            tv_time = (TextView) itemView.findViewById(R.id.time);
            tv_title = (TextView) itemView.findViewById(R.id.title);
        }
    }


}
