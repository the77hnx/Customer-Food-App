package com.example.customer_food;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customer_food.Adapter.ShopInfoAdapter;
import com.example.customer_food.Model.FoodItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ShopInfoActivity extends AppCompatActivity {

    private TextView textRestaurantName, textEvaluation, placeRes, val, timedel;
    private ImageView imageres;
    private Button share, totalPriceButton;
    private RecyclerView recyclerView;
    private ShopInfoAdapter shopInfoAdapter;
    private ArrayList<FoodItem> categoryList;
    private double totalPrice = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_shop_info);

        // Initialize views
        imageres = findViewById(R.id.imageres);
        textRestaurantName = findViewById(R.id.text_restaurant_name);
        textEvaluation = findViewById(R.id.text_evaluation);
        placeRes = findViewById(R.id.placeres_shopinfo);
        val = findViewById(R.id.val);
        timedel = findViewById(R.id.timedel);
        share = findViewById(R.id.button);
        totalPriceButton = findViewById(R.id.totalPriceButton);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView_sinfo);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home); // Change this based on the activity

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    // Navigate to ShopsActivity
                    startActivity(new Intent(ShopInfoActivity.this, ShopsActivity.class));
                    return true;
                } else if (itemId == R.id.navigation_basket) {
                    // Navigate to OrderSummaryActivity
                    startActivity(new Intent(ShopInfoActivity.this, OrderSummaryActivity.class));
                    return true;
                } else if (itemId == R.id.navigation_orders) {
                    // Navigate to ProfileActivity
                    startActivity(new Intent(ShopInfoActivity.this, HistoryOrdersActivity.class));
                    return true;
                }
                return false;
            }
        });
        // Initialize RecyclerView
        initRecyclerView();

        // Set click listener for the total price button
        totalPriceButton.setOnClickListener(v -> {
            ArrayList<FoodItem> filteredItems = new ArrayList<>();
            for (FoodItem item : categoryList) {
                if (item.getCount() > 0) {
                    filteredItems.add(item);
                }

                Intent intent = new Intent(ShopInfoActivity.this, OrderSummaryActivity.class);
                intent.putParcelableArrayListExtra("filteredItems", filteredItems);
                startActivity(intent);}

        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String restaurantName = extras.getString("restaurantName", "");
            String restaurantLocation = extras.getString("restaurantLocation", "");
            String restaurantStatus = extras.getString("restaurantStatus", "");

            // Example of setting data
            textRestaurantName.setText(restaurantName);
            textEvaluation.setText(restaurantStatus);
            placeRes.setText(restaurantLocation);
            val.setText("100 DZD");
            timedel.setText("0:30 - 1:30");
        }
        // Initial total price calculation
        calculateTotalPrice();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        categoryList = new ArrayList<>();
        categoryList.add(new FoodItem("بيتزا", 10.0, 0));
        categoryList.add(new FoodItem("هامبرغر", 20.0, 0));
        categoryList.add(new FoodItem("طاكوس", 30.0, 0));

        shopInfoAdapter = new ShopInfoAdapter(categoryList, this::calculateTotalPrice);
        recyclerView.setAdapter(shopInfoAdapter);
    }

    private void calculateTotalPrice() {
        totalPrice = 0.0;
        for (FoodItem item : categoryList) {
            totalPrice += item.getPrice() * item.getCount();
        }
        totalPriceButton.setText("السعر الاجمالي : " + totalPrice + "دج");
    }
}