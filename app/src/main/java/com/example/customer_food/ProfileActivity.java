package com.example.customer_food;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        Button btnExitAccount = findViewById(R.id.btnExitAccount);
        Button btnDeleteAccount = findViewById(R.id.btnDeleteAccount);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView_profile);
        bottomNavigationView.setSelectedItemId(R.id.navigation_profile); // Change this based on the activity

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    // Navigate to ShopsActivity
                    startActivity(new Intent(ProfileActivity.this, ShopsActivity.class));
                    return true;
                } else if (itemId == R.id.navigation_following) {
                    // Show toast indicating following action
                    startActivity(new Intent(ProfileActivity.this, OrderSummaryActivity.class ));
                    return true;
                } else if (itemId == R.id.navigation_basket) {
                    // Navigate to OrderSummaryActivity
                    startActivity(new Intent(ProfileActivity.this, HistoryOrdersActivity.class));
                    return true;
                } else if (itemId == R.id.navigation_profile) {
                    // Navigate to ProfileActivity
                    startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
                    return true;
                }
                return false;
            }
        });


        btnExitAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle exit account button click
                exitAccount();
            }
        });

        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete account button click
                deleteAccount();
            }
        });
    }

    private void exitAccount() {
        // Replace with your exit account logic
        // For example, navigate back to the previous screen or perform logout actions
        // This is a placeholder method, implement as per your application's requirements
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);

    }

    private void deleteAccount() {
        // Replace with your delete account logic
        // For example, show confirmation dialog and delete user data from backend
        // This is a placeholder method, implement as per your application's requirements
    }
}
