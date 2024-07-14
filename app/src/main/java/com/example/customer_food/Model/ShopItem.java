package com.example.customer_food.Model;

public class ShopItem {
    private String restaurantName;
    private String restaurantLocation;
    private String restaurantValue;
    private String restaurantStatus;

    public ShopItem(String restaurantName, String restaurantLocation, String restaurantValue, String restaurantStatus) {
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
        this.restaurantValue = restaurantValue;
        this.restaurantStatus = restaurantStatus;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }

    public String getRestaurantValue() {
        return restaurantValue;
    }

    public void setRestaurantValue(String restaurantValue) {
        this.restaurantValue = restaurantValue;
    }


    public String getRestaurantStatus() {
        return restaurantStatus;
    }

    public void setRestaurantStatus(String restaurantStatus) {
        this.restaurantStatus = restaurantStatus;
    }
}
