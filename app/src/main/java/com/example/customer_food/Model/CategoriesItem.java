package com.example.customer_food.Model;

public class CategoriesItem {
    private String categoryName;

    public CategoriesItem(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
