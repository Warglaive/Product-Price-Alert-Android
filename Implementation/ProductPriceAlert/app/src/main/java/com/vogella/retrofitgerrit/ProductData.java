package com.vogella.retrofitgerrit;

import android.media.Image;

public class ProductData {
    private String name;
    private double price;
    private Image image;
    private String description;

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public Image getImage() {
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

    public void setImage(Image image){
        this.image = image;
    }

}
