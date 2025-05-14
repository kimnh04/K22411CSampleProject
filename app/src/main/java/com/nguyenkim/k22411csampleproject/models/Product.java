package com.nguyenkim.k22411csampleproject.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String quantity;
    private double price;
    private int cate_id;
    private String image;

    public Product() {
    }

    public Product(int id, String name, String quantity, double price, int cate_id, String image) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.cate_id = cate_id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return id + " - " + name + "\nQuantity: " + quantity + "\nPrice: $" + price + "\nCategory ID: " + cate_id + "\nImage: " + image;
    }
}