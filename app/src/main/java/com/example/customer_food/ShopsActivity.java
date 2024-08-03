package com.example.customer_food;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.customer_food.Adapter.CategoriesAdapter;
import com.example.customer_food.Adapter.PopularRestaurantsAdapter;
import com.example.customer_food.Model.CategoriesItem;
import com.example.customer_food.Model.ShopItem;

import java.util.ArrayList;


public class ShopsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPop;
    private RecyclerView recyclerViewCat;
    private PopularRestaurantsAdapter popularRestaurantsAdapter;
    private CategoriesAdapter categoriesAdapter;
    private EditText searchEditText;
    private Button searchIcon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_shops);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView_shops);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home); // Change this based on the activity

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    // Navigate to ShopsActivity
                    startActivity(new Intent(ShopsActivity.this, ShopsActivity.class));
                    return true;
                } else if (itemId == R.id.navigation_following) {
                    // Show toast indicating following action
                    Toast.makeText(ShopsActivity.this, "لم تتابع اي شيء بعد", Toast.LENGTH_SHORT).show();
                    return false;
                } else if (itemId == R.id.navigation_basket) {
                    // Navigate to OrderSummaryActivity
                    startActivity(new Intent(ShopsActivity.this, HistoryOrdersActivity.class));
                    return true;
                } else if (itemId == R.id.navigation_profile) {
                    // Navigate to ProfileActivity
                    startActivity(new Intent(ShopsActivity.this, ProfileActivity.class));
                    return true;
                }
                return false;
            }
        });

        // Initialize RecyclerViews
        recyclerViewPop = findViewById(R.id.popularRestaurantsRecyclerViewver);
        recyclerViewCat = findViewById(R.id.popularRestaurantsRecyclerViewhor);

        // Initialize searchEditText
        searchEditText = findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        // Setup RecyclerViews
        setupPopularRestaurantsRecyclerView();
        setupCategoriesRecyclerView();

        // Set click listeners
        popularRestaurantsAdapter.setOnItemClickListener(new PopularRestaurantsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ShopItem item) {
                Intent intent = new Intent(ShopsActivity.this, ShopInfoActivity.class);
                intent.putExtra("restaurantName", item.getRestaurantName()); // Pass restaurant name
                intent.putExtra("restaurantLocation", item.getRestaurantLocation()); // Pass restaurant location
                intent.putExtra("restaurantStatus", item.getRestaurantValue());
                startActivity(intent);
            }
        });


        categoriesAdapter.setOnItemClickListener(new CategoriesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Replace with your logic to handle category click
                // For demonstration, opening ShopInfoActivity directly
                //        Intent intent = new Intent(ShopsActivity.this, ShopInfoActivity.class);
//                startActivity(intent);
                // You may want to pass data to ShopInfoActivity based on the clicked category
            }
        });
    }

    private void setupPopularRestaurantsRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewPop.setLayoutManager(layoutManager);

        ArrayList<ShopItem> shopItemList = new ArrayList<>();
        shopItemList.add(new ShopItem("بيتزيريا الحرية", "الوادي", "5.0", "مفتوح"));
        shopItemList.add(new ShopItem("مملكة البروتين", "الوادي", "2.1", "مغلق"));
        shopItemList.add(new ShopItem("ملك الطاكوس", "الوادي", "4.5", "مفتوح"));

        popularRestaurantsAdapter = new PopularRestaurantsAdapter(this, shopItemList);
        recyclerViewPop.setAdapter(popularRestaurantsAdapter);
    }

    private void setupCategoriesRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCat.setLayoutManager(layoutManager);

        ArrayList<CategoriesItem> categoryList = new ArrayList<>();
        categoryList.add(new CategoriesItem("مطاعم"));
        categoryList.add(new CategoriesItem("بيتزيريا"));
        categoryList.add(new CategoriesItem("مشاوي"));
        categoryList.add(new CategoriesItem("حلويات"));
        categoryList.add(new CategoriesItem("اخرى"));


        categoriesAdapter = new CategoriesAdapter(this, categoryList);
        recyclerViewCat.setAdapter(categoriesAdapter);
    }
    private void filterData(String searchText) {
        popularRestaurantsAdapter.filter(searchText);
        categoriesAdapter.filter(searchText);
    }
}