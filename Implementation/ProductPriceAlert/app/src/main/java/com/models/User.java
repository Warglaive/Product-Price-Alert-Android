package com.models;

import com.vogella.retrofitgerrit.ProductData;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String password;
    private String role;
    private List<String> maxPrice = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    /**
     * validate input and create new instance
     * @param name
     * @param email
     * @param password
     * @param role
     */
    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setMax(String price){
        this.maxPrice.add(price);
    }

    public String getMax(int i){
        return this.maxPrice.get(i);
    }

    public List<String> getMaxPrices(){
        return this.maxPrice;
    }

    public void setProduct(Product product){
        this.products.add(product);
    }

    public Product getProduct(int i){
        return this.products.get(i);
    }

    public List<Product> getProducts(){
        return this.products;
    }
}
