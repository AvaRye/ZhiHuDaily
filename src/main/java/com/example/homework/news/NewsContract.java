package com.example.homework.news;

import com.example.homework.data.Translation;

public interface NewsContract {
    interface NewsUIView {
        void onError();

        void initItems(Translation translation);

        //        void refreshItems(List<Translation.StoriesBean> dataSet, List<Translation.TopStoriesBean> topDataSet, List<View> viewList);
        void loadUpItems(Translation translation);
    }

    interface NewsPresenter {
        void initData();

        void refreshData();

        void onLoadMoreData();
    }
}
