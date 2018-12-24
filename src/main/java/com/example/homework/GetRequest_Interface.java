package com.example.homework;

import com.example.homework.data.Translation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetRequest_Interface {
    @GET("news/{date}")
    Call<Translation> getCall(@Path("date") String date);
}
