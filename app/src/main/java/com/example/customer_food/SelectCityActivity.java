package com.example.customer_food;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customer_food.Adapter.SelectCityAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectCityActivity extends AppCompatActivity {

    private RecyclerView stateRecyclerView;
    private EditText searchEditText;
    private Button searchIcon;

    private ArrayList<String> cities = new ArrayList<>(Arrays.asList("الحي الجديد", "الحرية", "السعادة", "1 نوفمبر", "5 جويلية", "الفتح", "النصر", "وسط المدينة"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_select_city);

        stateRecyclerView = findViewById(R.id.city_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        stateRecyclerView.setLayoutManager(layoutManager);
        final SelectCityAdapter adapter = new SelectCityAdapter(cities);
        stateRecyclerView.setAdapter(adapter);

        // Setup search EditText
        searchEditText = findViewById(R.id.searchEditText_select);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Initialize searchIcon and set click listener
        searchIcon = findViewById(R.id.searchIcon);
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString().trim();
                adapter.filter(query);
            }
        });

        adapter.setOnItemClickListener(new SelectCityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click
                String selectedCity = cities.get(position);
                Intent intent = new Intent(SelectCityActivity.this, ShopsActivity.class);
                intent.putExtra("selectedCity", selectedCity);
                startActivity(intent);
            }
        });
    }
}
