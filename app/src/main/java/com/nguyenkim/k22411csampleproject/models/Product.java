package com.nguyenkim.k22411csampleproject.models;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String name;
    private int quantity;
    private double price;
    private int cate_id;
    private int image_id;
    public Product() {
    }

    public Product(int id, String name, int quantity, double price, int image_id) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image_id = image_id;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getCate_id() {
        return cate_id;
    }

    public int getImageId() {
        return image_id;
    }

    @Override
    public String toString() {
        return id+"\t"+name+"\t"+price; // Return the product name for display
    }
}