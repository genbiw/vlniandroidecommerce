package com.example.vlnie_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class user extends AppCompatActivity {

    private String name, gender, country, city, address, email, phone, decodedString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ImageView main_icon = findViewById(R.id.main_icon);

        TextView main_scene = findViewById(R.id.main_scene);
        TextView userName = findViewById(R.id.userName);
        TextView userGender = findViewById(R.id.userGender);
        TextView userAge = findViewById(R.id.userAge);
        TextView userCountry = findViewById(R.id.userCountry);
        TextView userCity = findViewById(R.id.userCity);
        TextView userStreet = findViewById(R.id.userStreet);
        TextView userEmail = findViewById(R.id.userEmail);
        TextView userPhone = findViewById(R.id.userPhone);

        Button saveButton = findViewById(R.id.saveButton);
        Button logOutButton = findViewById(R.id.logOutButton);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut(user.this);
            }
        });

        main_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToMainPage(user.this);
            }
        });

        main_scene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToMainPage(user.this);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        Boolean isAuth = sharedPreferences.getBoolean("isAuth", false);
        String token = sharedPreferences.getString("token", null);

        // Check if the token is null or empty
        if (token != null && !token.isEmpty()) {
            try {
                // Extract the payload part of the JWT (the second part after the first dot)
                String[] tokenParts = token.split("\\."); // Splitting the token into three parts
                if (tokenParts.length == 3) {
                    // Decode the payload (second part) of the JWT
                    byte[] decodeBytes = Base64.decode(tokenParts[1], Base64.URL_SAFE);
                    decodedString = new String(decodeBytes, "UTF-8");

                    // Parse the JSON data from the token payload
                    JSONObject jsonObject = new JSONObject(decodedString);

                    // Set the JSON data to respective TextViews
                    userName.setText(jsonObject.optString("userName"));
                    userGender.setText(jsonObject.optString("gender"));
                    userAge.setText(String.valueOf(jsonObject.optInt("age")));
                    userCountry.setText(jsonObject.optString("country"));
                    userCity.setText(jsonObject.optString("city"));
                    userStreet.setText(jsonObject.optString("address"));
                    userEmail.setText(jsonObject.optString("email"));
                    userPhone.setText(jsonObject.optString("phoneNumber"));
                } else {
                    decodedString = "Invalid token format";
                }
            } catch (UnsupportedEncodingException e) {
                // Handle unsupported encoding
                decodedString = "Error decoding token: Unsupported encoding.";
            } catch (IllegalArgumentException e) {
                // Handle invalid Base64 token
                decodedString = "Error decoding token: Invalid Base64 input.";
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Handle the case where token is null or empty
            decodedString = "No token found";
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData(user.this);
            }
        });
    }

    private static void GoToMainPage(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    private void showData(Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Token data is: ")
                .setMessage(decodedString)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    private void logOut(Context context){
        SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Clears all data
        editor.apply(); // Apply changes asynchronously

        // Check if logOut was successful
        boolean isAuthRemoved = !sharedPreferences.contains("isAuth");
        boolean isTokenRemoved = !sharedPreferences.contains("token");

        if (isAuthRemoved && isTokenRemoved) {
            // Log out was successful; proceed
            GoToMainPage(context);
        } else {
            // Log out unsuccessful; show an error message
            new AlertDialog.Builder(context)
                    .setTitle("Logout Error")
                    .setMessage("Logout was unsuccessful. Please try again.")
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        }
    }
}
