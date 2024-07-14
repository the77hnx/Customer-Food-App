package com.example.customer_food;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OtpActivity extends AppCompatActivity {

    private EditText inputcode1, inputcode2, inputcode3, inputcode4, inputcode5, inputcode6;
    private Button buyButton;
    private TextView resendTextView;

    // Timer variables
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 60000; // 60 seconds
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_otp);

        // Initialize EditTexts
        inputcode1 = findViewById(R.id.inputcode1);
        inputcode2 = findViewById(R.id.inputcode2);
        inputcode3 = findViewById(R.id.inputcode3);
        inputcode4 = findViewById(R.id.inputcode4);
        inputcode5 = findViewById(R.id.inputcode5);
        inputcode6 = findViewById(R.id.inputcode6);

        // Initialize Button
        buyButton = findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateOTP();
            }
        });

        // Initialize Resend TextView
        resendTextView = findViewById(R.id.resendTextView);
        resendTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendOTP();
            }
        });

        // Start countdown timer
        startTimer();
    }

    private void validateOTP() {
        String otp1 = inputcode1.getText().toString().trim();
        String otp2 = inputcode2.getText().toString().trim();
        String otp3 = inputcode3.getText().toString().trim();
        String otp4 = inputcode4.getText().toString().trim();
        String otp5 = inputcode5.getText().toString().trim();
        String otp6 = inputcode6.getText().toString().trim();

        // Check if all fields are filled
        if (otp1.isEmpty() || otp2.isEmpty() || otp3.isEmpty() || otp4.isEmpty() || otp5.isEmpty() || otp6.isEmpty()) {
            Toast.makeText(this, "الرجاء ادخال جميع الاكواد", Toast.LENGTH_SHORT).show();
            return;
        }

        // Concatenate OTP
        String otp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;

        // Example: Check if OTP is valid (replace with your actual OTP validation logic)
        if (otp.equals("123456")) { // Replace with your actual OTP validation logic
            Toast.makeText(this, "تم التحقق من الرمز بنجاح", Toast.LENGTH_SHORT).show();
            // Example: Navigate to the location page upon successful validation
            navigateToLocationPage();
        } else {
            Toast.makeText(this, "الرمز غير صحيح", Toast.LENGTH_SHORT).show();
            // Example: Handle incorrect OTP case (e.g., clear input fields)
            clearInputFields();
        }
    }

    private void navigateToLocationPage() {
        // Replace with your intent to navigate to the location page
         Intent intent = new Intent(OtpActivity.this, LocationActivity.class);
         startActivity(intent);
         finish();
    }

    private void resendOTP() {
        // Implement resend OTP logic here
        // Example: Trigger sending a new OTP via SMS or email

        // Start countdown timer again
        if (!timerRunning) {
            startTimer();
        } else {
            resetTimer();
        }
    }

    private void startTimer() {

        resendTextView.setEnabled(false);
        resendTextView.setAlpha(0.5f);

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                updateTimer();
            }
        }.start();

        timerRunning = true;
    }

    private void resetTimer() {
        countDownTimer.cancel();
        timeLeftInMillis = 60000;
        startTimer();
    }

    private void updateTimer() {
        int seconds = (int) (timeLeftInMillis / 1000);
        resendTextView.setText("اعادة الارسال (" + seconds + ")");
        if (seconds <= 0) {
            resendTextView.setText("اعادة الارسال");
        }
    }

    private void clearInputFields() {
        inputcode1.setText("");
        inputcode2.setText("");
        inputcode3.setText("");
        inputcode4.setText("");
        inputcode5.setText("");
        inputcode6.setText("");
        inputcode1.requestFocus();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
