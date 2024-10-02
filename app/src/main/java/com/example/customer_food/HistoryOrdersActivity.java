package com.example.customer_food;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.customer_food.Adapter.HistoryOrderAdapter;
import com.example.customer_food.Model.Order;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoryOrdersActivity extends AppCompatActivity {

    private static final String TAG = "HistoryOrdersActivity";
    private RecyclerView recyclerView;
    private HistoryOrderAdapter adapter;
    private List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_history_orders);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView_ho);
        bottomNavigationView.setSelectedItemId(R.id.navigation_orders);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    startActivity(new Intent(HistoryOrdersActivity.this, ShopsActivity.class));
                    return true;
                } else if (itemId == R.id.navigation_basket) {
                    startActivity(new Intent(HistoryOrdersActivity.this, OrderSummaryActivity.class));
                    return true;
                } else if (itemId == R.id.navigation_orders) {
                    startActivity(new Intent(HistoryOrdersActivity.this, HistoryOrdersActivity.class));
                    return true;
                }
                return false;
            }
        });

        recyclerView = findViewById(R.id.recyclerViewOrders);
        orderList = new ArrayList<>();
        adapter = new HistoryOrderAdapter(this, orderList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fetchOrders();
    }

    private void fetchOrders() {
        String url = "http://192.168.1.34/fissa/Customer/History_orders.php"; // Ensure this URL is correct

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Create a JSON Array Request to fetch the data from the server
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            orderList.clear(); // Clear the existing orders
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                // Extract the data from the JSON object
                                int orderId = jsonObject.getInt("orderId");
                                String restaurantName = jsonObject.getString("storeName");
                                double deliveryPrice = jsonObject.getDouble("deliveryPrice");
                                double totalPrice = jsonObject.getDouble("orderPrice");
                                String orderDate = jsonObject.getString("orderDate");
                                String orderTime = jsonObject.getString("orderTime");
                                String orderStatus = jsonObject.getString("statusName");
                                String additionalInfomag = jsonObject.optString("additionalInfomag", "N/A");
                                String additionalInfoliv = jsonObject.optString("additionalInfoliv", "N/A");

                                // Create an Order object and add it to the order list
                                Order order = new Order(orderId, restaurantName, deliveryPrice, totalPrice, orderDate, orderTime, orderStatus, additionalInfomag, additionalInfoliv);
                                orderList.add(order);
                            }

                            // Notify the adapter about the data changes
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Log.e(TAG, "JSON parsing error: " + e.getMessage());
                            Toast.makeText(HistoryOrdersActivity.this, "Failed to parse data", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Volley error: " + error.getMessage());
                        Toast.makeText(HistoryOrdersActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the request queue
        requestQueue.add(jsonArrayRequest);
    }
}
