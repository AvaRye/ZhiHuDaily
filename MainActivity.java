package com.example.homework;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ItemBean> itemBeanList;
    private AdapterOne adapterOne;
    private SwipeRefreshLayout refreshLayout;
    public String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemBeanList = new ArrayList<>();
        initControls();
        sendRequestWithOkHttp();
        refresh();
        loadUp();
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://news-at.zhihu.com/api/3/news/latest")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObjectData = new JSONObject(jsonData);
            String date = jsonObjectData.getString("date");
            this.date = date;
            JSONArray jsonArray = jsonObjectData.getJSONArray("stories");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String image = jsonObject.getJSONArray("images").getString(0);
                String type = jsonObject.getString("type");
                String id = jsonObject.getString("id");
                String ga_prefix = jsonObject.getString("ga_prefix");
                ItemBean bean = new ItemBean();
                bean.setDate(date);
                bean.setText(title);
                bean.setImageUrl(image);
                itemBeanList.add(bean);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapterOne.getData(itemBeanList);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void initControls() {
        adapterOne = new AdapterOne(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerText);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterOne);
    }

    private void refresh(){
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void refreshData(){
        sendRequestWithOkHttp();
        adapterOne.refreshItems(itemBeanList);
    }

    private void loadUp(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalItemCount;
            private int firstVisibleItem;
            private int visibleItemCount;
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                onScrolled(recyclerView);
            }
            private void onScrolled(RecyclerView recyclerView){
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                totalItemCount = layoutManager.getItemCount();
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                visibleItemCount = recyclerView.getChildCount();
                if ((totalItemCount - visibleItemCount) <= firstVisibleItem) {
                    onLoadMore();
                }
            }

            private void sendRequestWithOkHttp() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url("https://news-at.zhihu.com/api/3/news/before/" + date)
                                    .build();
                            Response response = client.newCall(request).execute();
                            String responseData = response.body().string();
                            parseJSONWithJSONObject(responseData);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            private void onLoadMore() {
                initControls();
                sendRequestWithOkHttp();
            }
        });
    }
}
