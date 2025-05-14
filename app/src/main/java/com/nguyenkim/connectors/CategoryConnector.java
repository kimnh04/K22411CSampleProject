package com.nguyenkim.connectors;

import com.nguyenkim.k22411csampleproject.models.Category;
import com.nguyenkim.k22411csampleproject.models.ListCategory;

import java.util.ArrayList;

public class CategoryConnector {
    private ListCategory listCategory;

    public CategoryConnector() {
        listCategory = new ListCategory();
        listCategory.generateSampleDataset();
    }

    public ArrayList<Category> getAllCategories() {
        if (listCategory == null) {
            listCategory = new ListCategory();
            listCategory.generateSampleDataset();
        }
        return listCategory.getCategories();
    }

    public Category getCategoryById(int id) {
        if (listCategory == null) {
            listCategory = new ListCategory();
            listCategory.generateSampleDataset();
        }
        for (Category category : listCategory.getCategories()) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null; // Return null if no category is found with the given ID
    }
}