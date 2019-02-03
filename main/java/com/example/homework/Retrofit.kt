package com.example.homework

import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    private val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl("https://news-at.zhihu.com/api/3/news/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    var request = retrofit.create<GetRequest_Interface>(GetRequest_Interface::class.java!!)!!
}
