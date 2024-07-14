package com.example.customer_food;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LocationActivity extends AppCompatActivity {

    private static final int REQUEST_SELECT_LOCATION = 1;

    private Button locationButton;
    private Button selectButton;

    private String selectedCountry;
    private String selectedState;
    private String selectedMunicipal;
    private String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_location);

//        locationButton = findViewById(R.id.location_button);
        selectButton = findViewById(R.id.select_button);

//        locationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LocationActivity.this, SelectCountryActivity.class);
//                startActivity(intent);
//            }
//        });

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LocationActivity.this, SelectCountryActivity.class);
                startActivity(intent);
                finish();

//                if (selectedCountry == null || selectedState == null || selectedMunicipal == null || selectedCity == null) {
//                    Toast.makeText(LocationActivity.this, "قم بإختيار موقعك أولا", Toast.LENGTH_SHORT).show();
//                } else {
//                moveToShopsActivity();
//                }
            }
        });
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_SELECT_LOCATION && resultCode == RESULT_OK && data != null) {
//            selectedCountry = data.getStringExtra("selectedCountry");
//            selectedState = data.getStringExtra("selectedState");
//            selectedMunicipal = data.getStringExtra("selectedMunicipal");
//            selectedCity = data.getStringExtra("selectedCity");
//
//            String location = selectedCountry + "/" + selectedState + "/" + selectedMunicipal + "/" + selectedCity;
//            locationButtonText.setText(location);
//        }
//    }

    private void moveToShopsActivity() {
        Intent intent = new Intent(LocationActivity.this, ShopsActivity.class);
        startActivity(intent);
        finish();
    }
}
