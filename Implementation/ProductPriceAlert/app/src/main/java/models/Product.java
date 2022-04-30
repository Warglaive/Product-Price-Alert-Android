package models;


import android.media.Image;

public class Product {
    String name;
    double price;
    private Image image;

    public Product(String name, double price, Image image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Image getImage() {
        return image;
    }
}
