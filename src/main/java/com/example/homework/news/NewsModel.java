package com.example.homework.news;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.homework.GetRequest_Interface;
import com.example.homework.data.DateBean;
import com.example.homework.data.ItemBean;
import com.example.homework.data.TopItemBean;
import com.example.homework.data.Translation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsModel {
    List<ItemBean> dataSet = new ArrayList<>();
    List<ItemBean> topDataSet = new ArrayList<>();
    private com.example.homework.Retrofit retrofit = new com.example.homework.Retrofit();

//    private GetRequest_Interface request = retrofit.retrofit.create(GetRequest_Interface.class);

    public Call<Translation> update(String date) {
        return retrofit.request.getCall(date);
    }

//     Call<Translation> call = request.getCall("latest");
//        call.enqueue(new Callback<Translation>() {
//            @Override
//            public void onResponse(@NonNull Call<Translation> call, @NonNull retrofit2.Response<Translation> response) {
//                response.body().show();
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Translation> call, @NonNull Throwable t) {
//                Toast toast = Toast.makeText(MainActivity, "出错辽", Toast.LENGTH_SHORT);
//                toast.show();
//            }
//        });


/*    void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://news-at.zhihu.com/api/3/news/latest")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body() != null ? response.body().string() : null;
                    parseJSONWithJSONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Log.v("refresh", "刷新成功");

    }

    public void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObjectData = new JSONObject(jsonData);
            this.date = jsonObjectData.getString("date");
            DateBean dateBean = new DateBean();
            dateBean.setDate(date);
            dataSet.add(new ItemBean());//充当header占掉的位置orz
            dataSet.add(dateBean);
            JSONArray jsonArray = jsonObjectData.getJSONArray("stories");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String image = jsonObject.getJSONArray("images").getString(0);
                String id = jsonObject.getString("id");
                ItemBean bean = new ItemBean();
                bean.setText(title);
                bean.setImageUrl(image);
                bean.setId(id);
                dataSet.add(bean);
            }

            //header
            JSONArray jsonArray1 = jsonObjectData.getJSONArray("top_stories");
            for (int j = 0; j < jsonArray1.length(); j++) {
                JSONObject jsonObject = jsonArray1.getJSONObject(j);
                String topTitle = jsonObject.getString("title");
                String topImage = jsonObject.getString("image");
                String topId = jsonObject.getString("id");
                TopItemBean bean = new TopItemBean();
                bean.setText(topTitle);
                bean.setImageUrl(topImage);
                bean.setId(topId);
                topDataSet.add(bean);
//                viewList.add(new View());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    void sendRequestWithOkHttp2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://news-at.zhihu.com/api/3/news/before/" + date)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body() != null ? response.body().string() : null;
                    parseJSONWithJSONObject2(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJSONObject2(String jsonData) {
        try {
            JSONObject jsonObjectData = new JSONObject(jsonData);
            final String date = jsonObjectData.getString("date");
            this.date = date;
            DateBean dateBean = new DateBean();
            dateBean.setDate(date);
            dataSet.add(dateBean);
            JSONArray jsonArray = jsonObjectData.getJSONArray("stories");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String image = jsonObject.getJSONArray("images").getString(0);
                String id = jsonObject.getString("id");
                ItemBean bean = new ItemBean();
                bean.setText(title);
                bean.setImageUrl(image);
                bean.setId(id);
                dataSet.add(bean);
            }
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    adapterOne.initItems2(dataSet);
//                }
//            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

*/
}
