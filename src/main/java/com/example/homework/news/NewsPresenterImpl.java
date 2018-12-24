package com.example.homework.news;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.homework.data.Translation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPresenterImpl implements NewsContract.NewsPresenter {
    private NewsModel newsModel = new NewsModel();
    private NewsContract.NewsUIView newsUIView;

    public static String date;


    public NewsPresenterImpl(NewsContract.NewsUIView newsUIView) {
        this.newsUIView = newsUIView;
    }


    @Override
    public void initData() {
        Call<Translation> call = newsModel.update("latest");
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
//                String responseData = response.body() != null ? response.body().toString() : null;
//                newsModel.parseJSONWithJSONObject(responseData);
                Translation translation = response.body();
                if (translation != null) {
                    date = translation.getDate();
                    newsUIView.initItems(translation);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Translation> call, @NonNull Throwable t) {
                newsUIView.onError();
                t.printStackTrace();
            }
        });
//        newsModel.sendRequestWithOkHttp();

    }


    @Override
    public void refreshData() {
        newsModel.dataSet.clear();
        newsModel.topDataSet.clear();
        initData();
//        newsModel.sendRequestWithOkHttp();
        Log.v("refresh", "下拉刷新");
    }

    @Override
    public void onLoadMoreData() {
        Log.v("onLoadMore", "加载发送");
        Call<Translation> call = newsModel.update("before/" + date);
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
//                String responseData = response.body() != null ? response.body().toString() : null;
//                newsModel.parseJSONWithJSONObject(responseData);
                Translation translation = response.body();
                if (translation != null) {
                    date = translation.getDate();
                    newsUIView.loadUpItems(translation);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Translation> call, @NonNull Throwable t) {
                newsUIView.onError();
                t.printStackTrace();
            }
        });
//        newsModel.sendRequestWithOkHttp2();
//        newsUIView.loadUpItems(newsModel.dataSet);
    }
}
