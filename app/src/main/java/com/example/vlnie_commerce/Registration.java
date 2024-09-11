package com.example.vlnie_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.icu.number.CompactNotation;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Registration extends AppCompatActivity {

    private TextInputEditText nameInput, ageInput, countryInput, cityInput, addressInput, emailInput, passwordInput, confirmPasswordInput, phoneInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ImageView mainIcon = findViewById(R.id.main_icon);
        TextView mainButton = findViewById(R.id.main_scene);
        TextView logInPage = findViewById(R.id.loginPage);

        nameInput = findViewById(R.id.nameInput);
        ageInput = findViewById(R.id.ageInput);
        countryInput = findViewById(R.id.countryInput);
        cityInput = findViewById(R.id.cityInput);
        addressInput = findViewById(R.id.addressInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        phoneInput = findViewById(R.id.phoneInput);


        Button buttonRegistration = findViewById(R.id.buttonRegistration);


        mainIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainPage(Registration.this);
            }
        });

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainPage(Registration.this);
            }
        });

        logInPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginPage(Registration.this);
            }
        });

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCollectedData(Registration.this);
            }
        });
    }

    public static void goToMainPage (Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void goToLoginPage (Context context){
        Intent intent = new Intent(context, LoginPage.class);
        context.startActivity(intent);
    }

    public void showCollectedData (Context context){
        String name = nameInput.getText().toString();
        String age = ageInput.getText().toString();
        String country = countryInput.getText().toString();
        String city = cityInput.getText().toString();
        String address = addressInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String confirmPassword = confirmPasswordInput.getText().toString();
        String phone = phoneInput.getText().toString();

        // Formatting the collected data into a single message
        String message = "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Country: " + country + "\n" +
                "City: " + city + "\n" +
                "Address: " + address + "\n" +
                "Email: " + email + "\n" +
                "Password: " + password + "\n" +
                "Confirm Password: " + confirmPassword + "\n" +
                "Phone: " + phone;

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}