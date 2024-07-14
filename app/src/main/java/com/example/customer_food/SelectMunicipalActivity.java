package com.example.customer_food;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customer_food.Adapter.SelectMunicipalAdapter;
import com.example.customer_food.Adapter.SelectStateAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectMunicipalActivity extends AppCompatActivity {


    private RecyclerView MunicipalrecyclerView;
    private ArrayList<String> Municipal = new ArrayList<>(Arrays.asList("باب الزوار", "باب الوادي", "باب الفلاقة", "باب العسل", "باب القرجوم", "باب البحر", "باب الوادي", "باب السعدون", "باب الجديد", "باب مايسة", "باب الخوانيج", "باب القبلي", "باب العقلة", "باب دكالة", "باب الزوار"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_select_municipal);


        MunicipalrecyclerView = findViewById(R.id.municipal_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        MunicipalrecyclerView.setLayoutManager(layoutManager);
        SelectMunicipalAdapter adapter = new SelectMunicipalAdapter(this,Municipal );
        MunicipalrecyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new SelectMunicipalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                String selectedCountry = countries.get(position);
                Intent intent = new Intent(SelectMunicipalActivity.this, SelectCityActivity.class);
//                intent.putExtra("selectedCountry", selectedCountry);
                startActivity(intent);
            }
        });


    }


}
