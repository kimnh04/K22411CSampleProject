package com.nguyenkim.k22411csampleproject.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ListProduct implements Serializable {
    private ArrayList<Product> products;

    public ListProduct() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void generateSampleDataset() {
        for (int i = 1; i <= 50; i++) {
            int id = i;
            String name = "Product " + i;
            String quantity = "Quantity " + i;
            double price = i * 10.0;
            int cate_id = i % 5 + 1; // Example category ID
            String image = "Image_" + i + ".png";
            Product product = new Product(id, name, quantity, price, cate_id, image);
            addProduct(product);
        }
    }
}