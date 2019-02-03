package com.example.homework.news

import android.util.Log

import com.example.homework.data.Translation

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsPresenterImpl internal constructor(private val newsUIView: NewsContract.NewsUIView) : NewsContract.NewsPresenter {
    private val newsModel = NewsModel()

    var date: String? = null
    private var translation: Translation? = null


    override fun initData() {
        val call = newsModel.update("latest")
        call.enqueue(object : Callback<Translation> {
            override fun onResponse(call: Call<Translation>, response: Response<Translation>) {
                translation = response.body()
                if (translation != null) {
                    date = translation!!.date
                    newsUIView.initItems(translation!!)
                }
            }

            override fun onFailure(call: Call<Translation>, t: Throwable) {
                newsUIView.onError()
                t.printStackTrace()
            }
        })
    }

    override fun refreshData() {
        val call = newsModel.update("latest")
        call.enqueue(object : Callback<Translation> {
            override fun onResponse(call: Call<Translation>, response: Response<Translation>) {
                translation = response.body()
                if (translation != null) {
                    date = translation!!.date
                    newsUIView.refreshItems(translation!!)
                }
            }

            override fun onFailure(call: Call<Translation>, t: Throwable) {
                newsUIView.onError()
                t.printStackTrace()
            }
        })
        Log.v("refresh", "下拉刷新")
    }

    override fun onLoadMoreData() {
        val call = newsModel.update2(date)
        call.enqueue(object : Callback<Translation> {
            override fun onResponse(call: Call<Translation>, response: Response<Translation>) {
                Log.v("onLoadMore", "发请求了吗")
                translation = response.body()
                if (translation != null) {
                    date = translation!!.date
                    newsUIView.loadUpItems(translation!!)
                    Log.v("onLoadMore", "拿到东西了吗")
                }
            }

            override fun onFailure(call: Call<Translation>, t: Throwable) {
                newsUIView.onError()
                t.printStackTrace()
            }
        })
    }
}
