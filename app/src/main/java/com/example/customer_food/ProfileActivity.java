package com.example.customer_food;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


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
