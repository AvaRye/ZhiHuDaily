package com.example.homework.detail

import android.content.Intent
import android.os.Bundle

import com.example.homework.data.Translation

import retrofit2.Call

class DetailModel {
    lateinit var id: String
    internal fun setIntent(secondActivity: SecondActivity) {
        val intent = secondActivity.intent
        val bundle = intent.extras
        val temp = bundle?.getInt("id") ?: 9605444
        this.id = temp.toString()
        //        sendRequestWithOkHttp();
    }

    //    private Retrofit retrofit = new Retrofit.Builder()
    //            .baseUrl("https://news-at.zhihu.com/api/3/")
    //            .addConverterFactory(GsonConverterFactory.create())
    //            .build();
    //    private GetRequest_Interface request = retrofit.retrofit.create(GetRequest_Interface.class);

    fun update(id: String): Call<Translation> {
        val retrofit = com.example.homework.Retrofit()
        return retrofit.request.getCall(id)
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
