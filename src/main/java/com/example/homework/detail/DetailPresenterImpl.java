package com.example.homework.detail;

import android.support.annotation.NonNull;

import com.example.homework.data.Translation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenterImpl implements DetailContract.DetailPresenter {
    private DetailContract.DetailUIView detailUIView;
    private DetailModel detailModel = new DetailModel();

    public DetailPresenterImpl(DetailContract.DetailUIView detailUIView) {
        this.detailUIView = detailUIView;
    }

    @Override
    public void initData(SecondActivity secondActivity) {
        detailModel.setIntent(secondActivity);
        Call<Translation> call = detailModel.update(detailModel.id);
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
//                String responseData = response.body() != null ? response.body().toString() : null;
//                newsModel.parseJSONWithJSONObject(responseData);
                Translation translation = response.body();
                if (translation != null) {
                    detailUIView.initView(translation.getBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Translation> call, @NonNull Throwable t) {
                detailUIView.onError();
                t.printStackTrace();
            }
        });
    }
}
