package com.example.homework.detail;

public interface DetailContract {
    interface DetailUIView {
        void initView(String body);
        void onError();
    }

    interface DetailPresenter {
        void initData(SecondActivity secondActivity);
    }
}
