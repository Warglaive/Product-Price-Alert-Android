package com.vogella.retrofitgerrit;

import android.media.Image;

public class ProductData {
    private String name;
    private double price;
    private String image;
    private String description;

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getImage() {
        return this.image;
    }

    public String getDescription(){
        return this.description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setImage(String image){
        this.image = image;
    }

}
