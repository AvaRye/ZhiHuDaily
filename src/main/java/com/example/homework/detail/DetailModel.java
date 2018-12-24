package com.example.homework.detail;

import android.content.Intent;
import android.os.Bundle;

import com.example.homework.data.Translation;

import retrofit2.Call;

public class DetailModel {
    private com.example.homework.Retrofit retrofit = new com.example.homework.Retrofit();
    public String id;

    void setIntent(SecondActivity secondActivity) {
        Intent intent = secondActivity.getIntent();
        Bundle bundle = intent.getExtras();
        int temp = bundle != null ? bundle.getInt("id") : 9605444;
        this.id = temp + "";
//        sendRequestWithOkHttp();
    }

//    private Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("https://news-at.zhihu.com/api/3/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();
//    private GetRequest_Interface request = retrofit.retrofit.create(GetRequest_Interface.class);

    public Call<Translation> update(String id) {
        return retrofit.request.getCall(id);
    }

 /*   private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://news-at.zhihu.com/api/3/news/" + id)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body() != null ? response.body().string() : null;
                    parseJSONWithJSONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String body = jsonObject.getString("body");
            String image = jsonObject.getString("image");
            String imageSource = jsonObject.getString("image_source");
            String title = jsonObject.getString("title");
            bean.setBody(body);
            bean.setImageUrl(image);
            bean.setText(title);
            bean.setImageSource(imageSource);

//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    webView.loadData(bean.getBody(), "text/html", "utf-8");
//                }
//            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    */
}
