package com.example.manoranga.touristmanager;

/**
 * Created by Manoranga on 11/30/2017.
 */

public class Thotels_items {
    private String Title,shordescription,img;
    private double rate;
    private int price;


    public Thotels_items(String title, String shordescription, String img, double rate, int price) {
        Title = title;
        this.shordescription = shordescription;
        this.img = img;
        this.rate = rate;
        this.price = price;
    }
    public String getTitle() {
        return Title;
    }

    public String getShordescription() {
        return shordescription;
    }

    public String getImg() {
        return img;
    }

    public double getRate() {
        return rate;
    }

    public int getPrice() {
        return price;
    }


}
