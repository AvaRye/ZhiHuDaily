package com.example.homework.news;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;


import com.example.homework.R;
import com.example.homework.data.ItemBean;
import com.example.homework.data.Translation;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsContract.NewsUIView {
    private RecyclerView recyclerView;
    private DotView dotView;
    private AdapterOne adapterOne;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isLoading = false;
    private NewsContract.NewsPresenter newsPresenter = new NewsPresenterImpl(this);

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initControls();
        refresh();
        loadUp();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initControls() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        swipeRefreshLayout = findViewById(R.id.refresh);
        adapterOne = new AdapterOne(this);
        recyclerView = findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterOne);
//        View footerView = LayoutInflater.from(this).inflate(R.layout.footer,null,false);//TODO:footer
    }

    public void init() {
        newsPresenter.initData();
    }

    public void refresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsPresenter.refreshData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void loadUp() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalItemCount;
            private int firstVisibleItem;
            private int visibleItemCount;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                onScrolled(recyclerView);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                totalItemCount = layoutManager.getItemCount();
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                visibleItemCount = recyclerView.getChildCount();
                Log.v("onScrolled", "滑动判定");
                if (((totalItemCount - visibleItemCount) <= (firstVisibleItem)) && !isLoading) {
                    Log.v("onScrolled", "加载判定");
                    isLoading = true;
                    newsPresenter.onLoadMoreData();
                }
            }
        });
    }

    @Override
    public void onError() {
        Toast toast = Toast.makeText(MainActivity.this, "出错辽", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void initItems(Translation translation) {
        adapterOne.initItems(translation);
    }

//    @Override
//    public void refreshItems(List<Translation.StoriesBean> dataSet, List<Translation.TopStoriesBean> topDataSet, List<View> viewList) {
//        adapterOne.initItems(dataSet, topDataSet);
//    }

    @Override
    public void loadUpItems(Translation translation) {
        adapterOne.initItems2(translation);
        isLoading = false;
    }
}
