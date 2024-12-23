package com.example.vlnie_commerce.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Login {

    private Context context;

    // Constructor to pass the context
    public Login(Context context) {
        this.context = context;
    }

    public String loginUser(String urlString, String email, String password) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "*/*");
            connection.setDoOutput(true); // Allow sending data in request body

            // Set timeout values
            connection.setConnectTimeout(10000); // 10 seconds to establish the connection
            connection.setReadTimeout(15000);    // 15 seconds to read the data

            // Construct JSON payload with the actual email and password passed to the function
            String jsonInputString = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}";
            Log.d("Login", "jsonInputString: " + jsonInputString);

            // Write the JSON payload to the output stream
            try (OutputStream os = connection.getOutputStream()) {
                OutputStreamWriter writer = new OutputStreamWriter(os, "UTF-8");
                writer.write(jsonInputString);
                writer.flush();
                writer.close();
            }

            // Get the response code
            int responseCode = connection.getResponseCode();
            Log.d("LoginAPI", "Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                // Get the response body
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    Log.d("LoginAPI", "Response Body: " + response.toString()); // Log the response body

                    // Assuming the response is in JSON format, parse the response to get the token
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    String token = jsonResponse.getString("token");  // Extract token from JSON response

                    // Store the token in SharedPreferences
                    SharedPreferences sharedPreferences = context.getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", token);  // Save token in SharedPreferences
                    editor.putBoolean("isAuth", true);  // Save isAuth in SharedPreferences
                    editor.apply();  // Apply changes to SharedPreferences

                    Log.d("LoginAPI", "Token stored in SharedPreferences: " + token);  // Log token for debugging
                    return response.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle parsing or IO exceptions
                }
            } else {
                // Log the error response
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "utf-8"))) {
                    StringBuilder errorResponse = new StringBuilder();
                    String errorLine;
                    while ((errorLine = br.readLine()) != null) {
                        errorResponse.append(errorLine.trim());
                    }
                    Log.e("LoginAPI", "Error Response Body: " + errorResponse.toString());
                    return null;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("LoginAPI", "Error in sending request: " + e.getMessage());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }
}
