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

import java.io.UnsupportedEncodingException;

public class user extends AppCompatActivity {

    private String name, gender, country, city, address, email, phone, decodedString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ImageView main_icon = findViewById(R.id.main_icon);

        main_icon.setOnClickListener(new View.OnClickListener() {
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
                } else {
                    decodedString = "Invalid token format";
                }
            } catch (UnsupportedEncodingException e) {
                // Handle unsupported encoding
                decodedString = "Error decoding token: Unsupported encoding.";
            } catch (IllegalArgumentException e) {
                // Handle invalid Base64 token
                decodedString = "Error decoding token: Invalid Base64 input.";
            }
        } else {
            // Handle the case where token is null or empty
            decodedString = "No token found";
        }

        Button saveButton = findViewById(R.id.saveButton);

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
}
