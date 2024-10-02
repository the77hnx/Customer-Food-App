package com.example.customer_food;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    private TextView displayTextView, descriptionTextView, addressTextView, passwordTextView, editGPStext;
    private EditText etNumber, etName, addressEditText, passwordEditText;
    private Button editBtn1, editBtn2, editBtn3, editBtnPassword, btnSave;
    private boolean isEditingName = false, isEditingDescription = false, isEditingAddress = false, isEditingPassword = false;
    private OkHttpClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        // Initialize views
        displayTextView = findViewById(R.id.displayTextView);
        descriptionTextView = findViewById(R.id.descriptiontv);
        addressTextView = findViewById(R.id.addresstvwritten);
        editGPStext = findViewById(R.id.editGPStext);
        etNumber = findViewById(R.id.etnumberedit);
        etName = findViewById(R.id.etNameedit);
        addressEditText = findViewById(R.id.addresset);
        passwordEditText = findViewById(R.id.passwordet);
        passwordTextView = findViewById(R.id.passtvwritten);
        editBtn1 = findViewById(R.id.editbtn1);
        editBtn2 = findViewById(R.id.editbtn2);
        editBtn3 = findViewById(R.id.editbtn3);
        editBtnPassword = findViewById(R.id.editbtn4);
        btnSave = findViewById(R.id.saveeditbtn);
        client = new OkHttpClient();

        // Bottom Navigation setup
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView_ep);
        bottomNavigationView.setSelectedItemId(R.id.navigation_profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(ProfileActivity.this, ShopsActivity.class));
                return true;
            } else if (itemId == R.id.navigation_following) {
                Toast.makeText(ProfileActivity.this, "لم تتابع اي شيء بعد", Toast.LENGTH_SHORT).show();
                return false;
            } else if (itemId == R.id.navigation_basket) {
                startActivity(new Intent(ProfileActivity.this, HistoryOrdersActivity.class));
                return true;
            } else if (itemId == R.id.navigation_profile) {
                startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
                return true;
            } else {
                return false;
            }
        });

        // Set click listeners for the buttons
        editBtn1.setOnClickListener(v -> {
            toggleEditText(etNumber, displayTextView, editBtn1, isEditingName);
            isEditingName = !isEditingName;
        });

        editBtn2.setOnClickListener(v -> {
            toggleEditText(etName, descriptionTextView, editBtn2, isEditingDescription);
            isEditingDescription = !isEditingDescription;
        });

        editBtn3.setOnClickListener(v -> {
            toggleEditText(addressEditText, addressTextView, editBtn3, isEditingAddress);
            isEditingAddress = !isEditingAddress;
        });

        editBtnPassword.setOnClickListener(v -> {
            toggleEditText(passwordEditText, passwordTextView, editBtnPassword, isEditingPassword);
            isEditingPassword = !isEditingPassword;
        });

        // Save button listener
        btnSave.setOnClickListener(v -> {
            saveAllEdits();
            String fullName = displayTextView.getText().toString();
            String email = descriptionTextView.getText().toString();
            String phone = addressTextView.getText().toString();
            String password = passwordTextView.getText().toString();
            String address = editGPStext.getText().toString();
            updateUserData(fullName, email,phone, password, address);
        });

        // Fetch and display user data
        fetchUserData();
    }

    private void toggleEditText(EditText editText, TextView textView, Button button, boolean isEditing) {
        if (isEditing) {
            // Save changes and switch to TextView
            String newText = editText.getText().toString();
            if (!TextUtils.isEmpty(newText)) {
                textView.setText(newText);
            }
            textView.setVisibility(View.VISIBLE);
            editText.setVisibility(View.GONE);
            button.setBackgroundResource(R.drawable.ic_edit);
        } else {
            // Switch to EditText
            editText.setText(textView.getText());
            textView.setVisibility(View.GONE);
            editText.setVisibility(View.VISIBLE);
            button.setBackgroundResource(R.drawable.ic_done);
        }
    }

    private void saveAllEdits() {
        if (etNumber.getVisibility() == View.VISIBLE) {
            String newNumber = etNumber.getText().toString();
            if (!TextUtils.isEmpty(newNumber)) {
                displayTextView.setText(newNumber);
            }
            etNumber.setVisibility(View.GONE);
            displayTextView.setVisibility(View.VISIBLE);
        }

        if (etName.getVisibility() == View.VISIBLE) {
            String newName = etName.getText().toString();
            if (!TextUtils.isEmpty(newName)) {
                descriptionTextView.setText(newName);
            }
            etName.setVisibility(View.GONE);
            descriptionTextView.setVisibility(View.VISIBLE);
        }

        if (addressEditText.getVisibility() == View.VISIBLE) {
            String newAddress = addressEditText.getText().toString();
            if (!TextUtils.isEmpty(newAddress)) {
                addressTextView.setText(newAddress);
            }
            addressEditText.setVisibility(View.GONE);
            addressTextView.setVisibility(View.VISIBLE);
        }

        if (passwordEditText.getVisibility() == View.VISIBLE) {
            String newPassword = passwordEditText.getText().toString();
            if (!TextUtils.isEmpty(newPassword)) {
                passwordTextView.setText(newPassword);  // Avoid showing passwords in plain text in real apps
            }
            passwordEditText.setVisibility(View.GONE);
            passwordTextView.setVisibility(View.VISIBLE);
        }
    }

    private void updateUserData( String fullName,String email, String phone, String password, String address) {
        String url = "http://192.168.1.34/fissa/Customer/Update_profile.php"; // Replace with your server's update URL

        // Create form body with updated user data
        RequestBody formBody = new FormBody.Builder()
                .add("fullName", fullName)
                .add("email", email)
                .add("phone", phone)
                .add("password", password)
                .add("coordonnees", address)
                .build();

        // Build the POST request for updating user data
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        // Log the request parameters
        Log.d("ProfileActivity", "Sending request with parameters " + ", fullName: " + fullName +  ", email: " + email + ", phone: " + phone + ", password: " + password + ", address: " + address);

        // Execute the request asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(ProfileActivity.this, "Failed to update user data", Toast.LENGTH_LONG).show();
                });
                Log.e("ProfileActivity", "Error: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    Log.d("ProfileActivity", "JSON Data: " + jsonData);
                    runOnUiThread(() -> {
                        Toast.makeText(ProfileActivity.this, "User data updated successfully", Toast.LENGTH_LONG).show();
                    });
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(ProfileActivity.this, "Error updating user data: " + response.message(), Toast.LENGTH_LONG).show();
                    });
                    Log.e("ProfileActivity", "Error: " + response.message());
                }
            }
        });
    }

    private void fetchUserData() {
        String url = "http://192.168.1.34/fissa/Customer/Get_profile.php";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(ProfileActivity.this, "Failed to fetch user data", Toast.LENGTH_LONG).show();
                });
                Log.e("ProfileActivity", "Error: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Log.d("ProfileActivity", "Raw Response Data: " + responseBody); // Log raw response

                    try {
                        // Check if the response is valid JSON
                        JSONObject jsonObject = new JSONObject(responseBody);
                        if (jsonObject.has("error")) {
                            // Handle error message
                            runOnUiThread(() -> {
                                Toast.makeText(ProfileActivity.this, jsonObject.optString("error"), Toast.LENGTH_LONG).show();
                            });
                        } else {
                            // Process user data
                            String fullName = jsonObject.optString("fullName", "N/A");
                            String phone = jsonObject.optString("phone", "N/A");
                            String address = jsonObject.optString("address", "N/A");
                            String email = jsonObject.optString("email", "N/A");
                            String password = jsonObject.optString("password", "N/A");

                            runOnUiThread(() -> {
                                displayTextView.setText(fullName);
                                descriptionTextView.setText(email);
                                addressTextView.setText(phone);
                                passwordTextView.setText(password);
                                editGPStext.setText(address);
                            });
                        }
                    } catch (JSONException e) {
                        runOnUiThread(() -> {
                            Toast.makeText(ProfileActivity.this, "Error parsing user data", Toast.LENGTH_LONG).show();
                        });
                        Log.e("ProfileActivity", "JSON parsing error: " + e.getMessage());
                    }
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(ProfileActivity.this, "Error fetching user data", Toast.LENGTH_LONG).show();
                    });
                    Log.e("ProfileActivity", "Response error: " + response.message());
                }
            }
        });
    }

}
