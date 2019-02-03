package com.example.homework

import com.example.homework.data.Translation

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetRequest_Interface {
    @GET("{date}")
    fun getCall(@Path("date") date: String): Call<Translation>

    @GET("before/{date}")
    fun getCall2(@Path("date") date: String): Call<Translation>
}
