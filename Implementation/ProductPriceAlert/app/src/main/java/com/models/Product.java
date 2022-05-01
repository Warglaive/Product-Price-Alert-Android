package com.models;


import android.media.Image;

public class Product {
    private String name;
    private double price;
    private Image image;
    private String description;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price, String description){
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Product(String name, double price, String description, Image image){
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

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
