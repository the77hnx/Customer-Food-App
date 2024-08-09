package com.example.customer_food;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customer_food.Adapter.ItemDetailsAdpter;
import com.example.customer_food.Model.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class DetailedOrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemDetailsAdpter adapter;
    private List<FoodItem> itemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_order); // Use your layout with RecyclerView

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView); // Your RecyclerView ID
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize item list and adapter
        itemList = new ArrayList<>();
        adapter = new ItemDetailsAdpter(this, itemList);
        recyclerView.setAdapter(adapter);

        // Populate the item list
        populateItems();
    }

    private void populateItems() {
        itemList.add(new FoodItem("Item 1", 10000.00,10, R.drawable.pizza));
        itemList.add(new FoodItem("Item 2", 100.00,10, R.drawable.pizza));
        itemList.add(new FoodItem("Item 3", 10.00,10, R.drawable.pizza));
        // Add more items as needed

        // Notify adapter of data change
        adapter.notifyDataSetChanged();
    }
}
