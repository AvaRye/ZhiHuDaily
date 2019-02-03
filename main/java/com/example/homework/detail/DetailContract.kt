package com.example.homework.detail

interface DetailContract {
    interface DetailUIView {
        fun initView(body: String)
        fun onError()
    }

    interface DetailPresenter {
        fun initData(secondActivity: SecondActivity)
    }
}
