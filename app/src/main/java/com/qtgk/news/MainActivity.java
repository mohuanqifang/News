package com.qtgk.news;

import android.app.Activity;
import android.content.Intent;;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.qtgk.adapter.TitleAdapter;
import com.qtgk.bean.NewsClassify;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<NewsClassify> list;
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        list.add(0, new NewsClassify(R.color.colorAccent, "头条", "toutiao"));
        list.add(1, new NewsClassify(R.color.colorAccent, "社会", "shehui"));
        list.add(2, new NewsClassify(R.color.colorAccent, "国内", "guonei"));
        list.add(3, new NewsClassify(R.color.colorAccent, "国际", "guoji"));
        list.add(4, new NewsClassify(R.color.colorAccent, "娱乐", "yule"));
        list.add(5, new NewsClassify(R.color.colorAccent, "体育", "tiyu"));
        list.add(6, new NewsClassify(R.color.colorAccent, "军事", "junshi"));
        list.add(7, new NewsClassify(R.color.colorAccent, "科技", "keji"));
        list.add(8, new NewsClassify(R.color.colorAccent, "财经", "caijing"));
        list.add(9, new NewsClassify(R.color.colorAccent, "时尚", "shishang"));
        TitleAdapter adapter= new TitleAdapter(this, list);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent(MainActivity.this, NewsListActivity.class);
                mIntent.putExtra("TYPE", list.get(position).getNamePY());
                startActivity(mIntent);
            }
        });
    }
}
