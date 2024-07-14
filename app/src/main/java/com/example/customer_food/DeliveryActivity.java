package com.example.customer_food;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.customer_food.Model.Order;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DeliveryActivity extends AppCompatActivity {

    private TextView commentMlTextView;
    private TextView posScoreMlTextView;
    private TextView productPriceTextView;
    private TextView deliveryPriceTextView;
    private TextView totalWithDeliveryTextView;
    private EditText textAdditionalDescription;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_delivery);

        // Initialize Views
        commentMlTextView = findViewById(R.id.namerestaurant);
        posScoreMlTextView = findViewById(R.id.pos_score_ml);
        productPriceTextView = findViewById(R.id.productPriceTextView_del);
        deliveryPriceTextView = findViewById(R.id.deliveryPriceTextView_del);
        totalWithDeliveryTextView = findViewById(R.id.totalPriceTextView_del);
        textAdditionalDescription = findViewById(R.id.textAdditionalDescription);
        confirmButton = findViewById(R.id.confirmButton);

        // Retrieve data from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String restaurantName = extras.getString("restaurantName", "");
            String orderCount = extras.getString("orderCount", "");
            String totalPrice = extras.getString("totalPrice", "");
            String deliveryPrice = extras.getString("deliveryPrice", "");
            String totalWithDelivery = extras.getString("totalWithDelivery", "");


            // Update TextViews
            commentMlTextView.setText("المطعم : " + restaurantName);
            posScoreMlTextView.setText("عدد الأطباق : " + orderCount);
            productPriceTextView.setText("" + totalPrice);
            deliveryPriceTextView.setText("" + deliveryPrice);
            totalWithDeliveryTextView.setText("" + totalWithDelivery);
        }

        // Apply WindowInsets to adjust layout with system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Handle Confirm Button click
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String additionalInfo = textAdditionalDescription.getText().toString().trim();

                // Retrieve necessary information from intent
                String restaurantName = extras.getString("restaurantName", "");
                String orderCount = extras.getString("orderCount", "");
                String totalPrice = extras.getString("totalPrice", "");
                String deliveryPrice = extras.getString("deliveryPrice", "");
                String totalWithDelivery = extras.getString("totalWithDelivery", "");
                String orderStatus = "Pending"; // You can set the initial order status here

                // Create a new Order object
                String orderNumber = getUserOrderNumber();
                Order newOrder = new Order(restaurantName, orderCount, totalPrice, deliveryPrice, totalWithDelivery, orderStatus, orderNumber);

                showConfirmationDialog(newOrder);
            }
        });
    }

    private void showConfirmationDialog(Order order) {
        new AlertDialog.Builder(DeliveryActivity.this)
                .setTitle("تأكيد الطلب")
                .setMessage("هل أنت متأكد من عملية الدفع")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showOrderConfirmedDialog(order);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showOrderConfirmedDialog(Order order) {
        new AlertDialog.Builder(DeliveryActivity.this)
                .setTitle("تأكيد الطلب")
                .setMessage("شكرا على ثقتك سيدي الزبون")
                .setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Prepare data to send to HistoryOrdersActivity
                        Intent intent = new Intent(DeliveryActivity.this, HistoryOrdersActivity.class);
                        intent.putExtra("restaurantName", commentMlTextView.getText().toString());
                        intent.putExtra("orderCount", posScoreMlTextView.getText().toString());
                        intent.putExtra("totalPrice", productPriceTextView.getText().toString());
                        intent.putExtra("deliveryPrice", deliveryPriceTextView.getText().toString());
                        intent.putExtra("totalWithDelivery", totalWithDeliveryTextView.getText().toString());
                        intent.putExtra("orderStatus", "In Preparation");
                        startActivity(intent);
                    }
                })
                .show();
    }

    private String getUserOrderNumber() {
        // Implement this method to return the user's order number
        // For example, you can fetch it from the user's profile or generate a unique number
        return "12345"; // Example order number
    }
}
