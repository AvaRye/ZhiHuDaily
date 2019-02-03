package com.example.homework.news

import com.example.homework.data.Translation

import retrofit2.Call

class NewsModel {
    fun update(date: String): Call<Translation> {
        val retrofit = com.example.homework.Retrofit()
        return retrofit.request.getCall(date)
    }

    fun update2(date: String?): Call<Translation> {
        val retrofit = com.example.homework.Retrofit()
        return retrofit.request.getCall2(date!!)
    }


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
