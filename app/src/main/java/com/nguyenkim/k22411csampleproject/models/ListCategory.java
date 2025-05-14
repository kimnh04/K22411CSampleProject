package com.nguyenkim.k22411csampleproject.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ListCategory implements Serializable {
    private ArrayList<Category> categories;

    public ListCategory() {
        categories = new ArrayList<>();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void generateSampleDataset() {
        for (int i = 1; i <= 50; i++) {
            int id = i;
            String name = "Category " + i;
            String description = "Description for Category " + i;
            Category category = new Category(id, name, description);
            addCategory(category);
        }
    }
}