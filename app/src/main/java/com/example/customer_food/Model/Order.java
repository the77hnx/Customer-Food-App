package com.example.customer_food.Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Order implements Serializable {
    private String restaurantName;
    private String orderCount;
    private String orderPrice;
    private String deliveryPrice;
    private String totalWithDelivery;
    private String orderStatus;
    private String orderNumber;
    private String dateOfDelivery;
    private String deliveryStatus;

    public Order(String restaurantName, String orderCount, String orderPrice, String deliveryPrice, String totalWithDelivery, String orderStatus, String orderNumber){
        this.restaurantName = restaurantName;
        this.orderCount = orderCount;
        this.orderPrice = orderPrice;
        this.deliveryPrice = deliveryPrice;
        this.totalWithDelivery = totalWithDelivery;
        this.orderStatus = orderStatus;
        this.orderNumber = orderNumber;
        this.dateOfDelivery = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        this.deliveryStatus = "قيد التحضير";
    }

    // Add getters for all fields
    public String getRestaurantName() {
        return restaurantName;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public String getTotalWithDelivery() {
        return totalWithDelivery;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getDateOfDelivery() {
        return dateOfDelivery;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }
}
