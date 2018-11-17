package com.example.homework;

public class ItemBean {
    private String text;
    private String imageUrl;
    private String date;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setDate(String date){this.date = date;}

    public String getDate(){return date;}
}
