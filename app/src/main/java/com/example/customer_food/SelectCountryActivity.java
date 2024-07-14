package com.example.customer_food;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customer_food.Adapter.SelectCountryAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectCountryActivity extends AppCompatActivity {

    private RecyclerView countryListView;
    private ArrayList<String> countries = new ArrayList<>(Arrays.asList("الجزائر", "مصر", "المغرب"));

; // Replace with your country list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_select_country);

        countryListView = findViewById(R.id.country_list_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        countryListView.setLayoutManager(layoutManager);
        SelectCountryAdapter adapter = new SelectCountryAdapter(this, countries);
        countryListView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SelectCountryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click here, e.g., start new activity
                Intent intent = new Intent(SelectCountryActivity.this, SelectStateActivity.class);
//                intent.putExtra("selectedCountry", countries.get(position)); // Example of passing data to next activity
                startActivity(intent);
            }
        });
    }
}
