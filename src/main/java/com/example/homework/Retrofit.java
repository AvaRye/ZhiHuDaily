package com.example.homework;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    private retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl("https://news-at.zhihu.com/api/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
}
