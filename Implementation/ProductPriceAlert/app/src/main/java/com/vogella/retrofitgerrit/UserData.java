package com.vogella.retrofitgerrit;

import com.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Used ONLY to communicate with the LB4
 */
public class UserData {
    //  @SerializedName(value = "name")
    private String name;
    // @SerializedName(value = "email")
    private String email;
    // @SerializedName(value = "password")
    private String password;
    // @SerializedName(value = "role")
    private String role;
    private String id;
    private List<String> maxPrice = new ArrayList<>();
    private List<ProductData> products = new ArrayList<>();

    public String getID(){
        return this.id;
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

    public void setProduct(ProductData productData){
        this.products.add(productData);
    }

    public ProductData getProduct(int i){
        return this.products.get(i);
    }

    public List<ProductData> getProducts(){
        return this.products;
    }
}