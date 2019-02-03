package com.example.homework.news

import com.example.homework.data.Translation

interface NewsContract {
    interface NewsUIView {
        fun onError()

        fun initItems(translation: Translation)

        fun refreshItems(translation: Translation)

        fun loadUpItems(translation: Translation)
    }

    interface NewsPresenter {
        fun initData()

        fun refreshData()

        fun onLoadMoreData()
    }
}
