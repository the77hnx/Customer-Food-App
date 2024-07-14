package com.example.customer_food;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customer_food.Adapter.HistoryOrderAdapter;
import com.example.customer_food.Model.Order;
import com.example.customer_food.Model.OrdersHistory;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HistoryOrdersActivity extends AppCompatActivity {

    private RecyclerView recyclerViewOrders;
    private List<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_history_orders);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView_ho);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    // Navigate to ShopsActivity
                    startActivity(new Intent(HistoryOrdersActivity.this, ShopsActivity.class));
                    return true;
                } else if (itemId == R.id.navigation_following) {
                    // Show toast indicating following action
                    Toast.makeText(HistoryOrdersActivity.this, "لم تتابع أي شيء بعد", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.navigation_basket) {
                    // Navigate to OrderSummaryActivity
                    startActivity(new Intent(HistoryOrdersActivity.this, OrderSummaryActivity.class));
                    return true;
                } else if (itemId == R.id.navigation_profile) {
                    // Navigate to ProfileActivity
                    startActivity(new Intent(HistoryOrdersActivity.this, ProfileActivity.class));
                    return true;
                }
                return false;
            }
        });
        // Initialize RecyclerView
        recyclerViewOrders = findViewById(R.id.recyclerViewOrders);
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve data from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String restaurantName = extras.getString("restaurantName");
            String orderCount = extras.getString("orderCount");
            String totalPrice = extras.getString("totalPrice");
            String deliveryPrice = extras.getString("deliveryPrice");
            String totalWithDelivery = extras.getString("totalWithDelivery");
            String orderStatus = extras.getString("orderStatus");

            // Generate or fetch the order number
            String orderNumber = generateOrderNumber(); // Implement this method to generate or fetch order number

            // Create a new Order object and add it to the list
            Order newOrder = new Order(restaurantName, orderCount, totalPrice, deliveryPrice, totalWithDelivery, orderStatus, orderNumber);
            orders.add(newOrder);
        }

        // Fetch additional orders from database (example method)
        orders.addAll(fetchOrdersFromDatabase());

        // Set adapter to RecyclerView
        if (!orders.isEmpty()) {
            HistoryOrderAdapter orderAdapter = new HistoryOrderAdapter(this, orders);
            recyclerViewOrders.setAdapter(orderAdapter);
        }
    }

    // Example method to fetch orders from database (replace with your actual implementation)
    private List<Order> fetchOrdersFromDatabase() {
        // Example implementation fetching orders from a database or API
        // Replace with your actual logic to fetch orders
        return OrdersHistory.getInstance().getOrders(); // Example method, replace with your actual database fetching logic
    }

    // Example method to generate or fetch order number
    private String generateOrderNumber() {
        // Implement logic to generate or fetch order number
        return "123456"; // Example order number
    }
}
