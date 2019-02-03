package com.example.homework.detail

import com.example.homework.data.Translation

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenterImpl(private val detailUIView: DetailContract.DetailUIView) : DetailContract.DetailPresenter {
    private val detailModel = DetailModel()

    override fun initData(secondActivity: SecondActivity) {
        detailModel.setIntent(secondActivity)
        val call = detailModel.update(detailModel.id)
        call.enqueue(object : Callback<Translation> {
            override fun onResponse(call: Call<Translation>, response: Response<Translation>) {
                //                String responseData = response.body() != null ? response.body().toString() : null;
                //                newsModel.parseJSONWithJSONObject(responseData);
                val translation = response.body()
                if (translation != null) {
                    detailUIView.initView(translation.body!!)
                }
            }

            override fun onFailure(call: Call<Translation>, t: Throwable) {
                detailUIView.onError()
                t.printStackTrace()
            }
        })
    }
}
