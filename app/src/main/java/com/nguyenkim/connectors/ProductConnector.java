package com.nguyenkim.connectors;

import com.nguyenkim.k22411csampleproject.models.Product;
import com.nguyenkim.k22411csampleproject.models.ListProduct;

import java.util.ArrayList;

public class ProductConnector {
    private ListProduct listProduct;

    public ProductConnector() {
        listProduct = new ListProduct();
        listProduct.generateSampleDataset();
    }

    public ArrayList<Product> getAllProducts() {
        if (listProduct == null) {
            listProduct = new ListProduct();
            listProduct.generateSampleDataset();
        }
        return listProduct.getProducts();
    }

    public ArrayList<Product> getProductsByCategoryId(int cateId) {
        if (listProduct == null) {
            listProduct = new ListProduct();
            listProduct.generateSampleDataset();
        }
        ArrayList<Product> results = new ArrayList<>();
        for (Product product : listProduct.getProducts()) {
            if (product.getCate_id() == cateId) {
                results.add(product);
            }
        }
        return results;
    }
}