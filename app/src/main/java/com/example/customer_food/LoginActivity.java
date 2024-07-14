package com.example.customer_food;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etFullname, etEmail, etPhoneNumber;
    private CheckBox checkboxRememberMe;
    private Button btnPhoneLogin;

    private static final String PREF_NAME = "login_preferences";
    private static final String KEY_FULLNAME = "fullname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE_NUMBER = "phone_number";
    private static final String KEY_REMEMBER_ME = "remember_me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        etFullname = findViewById(R.id.etfullname);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        checkboxRememberMe = findViewById(R.id.checkboxRememberMe);
        btnPhoneLogin = findViewById(R.id.btnPhoneLogin);

        btnPhoneLogin.setOnClickListener(v -> {
            String fullname = etFullname.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phoneNumber = etPhoneNumber.getText().toString().trim();

            if (fullname.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
                Toast.makeText(LoginActivity.this, "الرجاء إدخال جميع الحقول", Toast.LENGTH_SHORT).show();
            } else if (!isValidEmail(email)) {
                Toast.makeText(LoginActivity.this, "الرجاء إدخال بريد إلكتروني صالح", Toast.LENGTH_SHORT).show();
            } else {
                // Simulate login check (replace with your actual login logic)
                boolean isLoginValid = isLoginValid(fullname, email, phoneNumber);

                if (isLoginValid) {
                    // Save login info if "Remember Me" is checked
                    if (checkboxRememberMe.isChecked()) {
                        saveLoginPreferences(fullname, email, phoneNumber, true);
                    } else {
                        clearLoginPreferences();
                    }

                    // Navigate to OTP page (replace with your OTP activity)
                    Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                    startActivity(intent);
                    finish(); // Finish current activity to prevent back button from returning here
                } else {
                    Toast.makeText(LoginActivity.this, "بيانات تسجيل الدخول غير صحيحة", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Load saved login info if "Remember Me" was previously checked
        if (loadLoginPreferences()) {
            String savedFullname = getSharedPreferences(PREF_NAME, MODE_PRIVATE).getString(KEY_FULLNAME, "");
            String savedEmail = getSharedPreferences(PREF_NAME, MODE_PRIVATE).getString(KEY_EMAIL, "");
            String savedPhoneNumber = getSharedPreferences(PREF_NAME, MODE_PRIVATE).getString(KEY_PHONE_NUMBER, "");

            etFullname.setText(savedFullname);
            etEmail.setText(savedEmail);
            etPhoneNumber.setText(savedPhoneNumber);
            checkboxRememberMe.setChecked(true);
        }
    }

    private boolean isLoginValid(String fullname, String email, String phoneNumber) {
        // Replace with your actual login validation logic (e.g., check against a database)
        // This is a placeholder method
        return true; // For demo purposes, always return true
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void saveLoginPreferences(String fullname, String email, String phoneNumber, boolean rememberMe) {
        SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
        editor.putString(KEY_FULLNAME, fullname);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHONE_NUMBER, phoneNumber);
        editor.putBoolean(KEY_REMEMBER_ME, rememberMe);
        editor.apply();
    }

    private boolean loadLoginPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_REMEMBER_ME, false);
    }

    private void clearLoginPreferences() {
        SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
        editor.clear().apply();
    }
}
