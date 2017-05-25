package com.qtgk.news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.qtgk.adapter.NewsAdapter;
import com.qtgk.bean.NewsInfor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewsListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RequestQueue mRequestQueue;
    private String type;

    Response.Listener<String> listener = new Response.Listener<String>() {
        @Override
        public void onResponse(String string) {
            final ArrayList<NewsInfor> list = jsonParserInfor(string);
            if (list != null && list.size() != 0) {
                NewsAdapter adapter = new NewsAdapter(NewsListActivity.this, list);
                mRecyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(NewsListActivity.this, NewsInforActivity.class);
                        intent.putExtra("URL", list.get(position).getUrl());
                        startActivity(intent);
                    }
                });
            }

        }
    };

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        type = getIntent().getExtras().getString("TYPE");
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setOn
        loadNewsInfor();
    }

    protected void loadNewsInfor() {
        mRequestQueue = Volley.newRequestQueue(this);
        final HashMap<String, String> mHashMap = new HashMap<>();
        mHashMap.put("key", "66751330d687494e6003b25fb2a66165");
        mHashMap.put("type", type);
        StringRequest mStringRequest = new StringRequest(Request.Method.POST,
                "http://v.juhe.cn/toutiao/index?", listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return mHashMap;
            }

            @Override
            protected String getParamsEncoding() {
                return "utf-8";
            }
        };
        mRequestQueue.add(mStringRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRequestQueue.cancelAll(this);
    }

    private ArrayList<NewsInfor> jsonParserInfor(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            if (jsonObject.getString("reason").equals("成功的返回")
                    && jsonObject.getInt("error_code") == 0) {
                ArrayList<NewsInfor> list = new ArrayList<>();
                JSONObject jsonObjectChild = jsonObject.getJSONObject("result");
                if (jsonObjectChild.getInt("stat") == 1) {
                    JSONArray jsonArray = jsonObjectChild.getJSONArray("data");
                    for (int i = 0, len = jsonArray.length(); i < len; i++) {
                        JSONObject jsonObjectGrandson = jsonArray.getJSONObject(i);
                        NewsInfor mNewsInfor = new NewsInfor();
                        mNewsInfor.setAuthor_name(jsonObjectGrandson.optString("author_name"));
                        mNewsInfor.setCategory(jsonObjectGrandson.optString("category"));
                        mNewsInfor.setData(jsonObjectGrandson.optString("date"));
                        mNewsInfor.setTitle(jsonObjectGrandson.optString("title"));
                        mNewsInfor.setThumbnail_pic_s(jsonObjectGrandson.optString("thumbnail_pic_s"));
                        mNewsInfor.setUrl(jsonObjectGrandson.optString("url"));
                        list.add(i, mNewsInfor);
                        mNewsInfor = null;
                    }
                }
                return list;
            } else {
                return null;
            }
        } catch (JSONException e) {
            return null;
        }
    }
}
