package com.example.customer_food;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customer_food.Adapter.SelectCountryAdapter;
import com.example.customer_food.Adapter.SelectStateAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectStateActivity extends AppCompatActivity {


    private RecyclerView stateRecyclerView;
    private ArrayList<String> states = new ArrayList<>(Arrays.asList("أدرار", "الشلف", "الأغواط","أم البواقي", "باتنة", "بجاية", "بسكرة", "بشار", "البليدة", "البويرة", "تمنراست", "تبسة", "تلمسان", "تيارت", "تيبازة", "تيزي وزو", "الجزائر العاصمة", "الجلفة", "جيجل", "سطيف", "سعيدة", "سكيكدة", "سيدي بلعباس", "عنابة", "قالمة", "قسنطينة", "المدية", "مستغانم", "المسيلة", "معسكر", "ورقلة", "وهران", "البيض", "إليزي", "برج بوعريريج", "بومرداس", "الطارف", "تندوف", "تسمسيلت", "الوادي", "خنشلة", "سوق أهراس", "تيندوف", "تيسمسيلت", "النعامة", "الأغواط"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_select_state);


        stateRecyclerView = findViewById(R.id.state_list_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        stateRecyclerView.setLayoutManager(layoutManager);
        SelectStateAdapter adapter = new SelectStateAdapter(this, states);
        stateRecyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new SelectStateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                String selectedCountry = countries.get(position);
                Intent intent = new Intent(SelectStateActivity.this, SelectMunicipalActivity.class);
//                intent.putExtra("selectedCountry", selectedCountry);
                startActivity(intent);
            }
        });


    }


}
