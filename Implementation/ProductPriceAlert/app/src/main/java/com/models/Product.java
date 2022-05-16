package com.models;


public class Product {
    private String name;
    private double price;
    private String image;
    private String description;
    private String location;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price, String description){
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Product(String name, double price, String description, String image){
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Product(String name, double price, String description, String image, String location){
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.location = location;
    }

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

    public String getLocation(){
        return this.location;
    }
}
