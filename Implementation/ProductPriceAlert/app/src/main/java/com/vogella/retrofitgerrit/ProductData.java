package com.vogella.retrofitgerrit;

public class ProductData {
    private String name;
    private double price;
    private String image;
    private String description;
    private String location;

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLocation(String location) {
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

    public boolean hasDescription(){
        return this.description != null;
    }
    public boolean hasLocation(){
        return this.location != null;
    }

    public boolean hasImage(){
        return this.image != null;
    }

    @Override
    public String toString() {
        return "ProductData{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
