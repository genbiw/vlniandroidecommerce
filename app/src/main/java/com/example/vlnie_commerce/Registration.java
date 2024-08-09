package com.example.vlnie_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.icu.number.CompactNotation;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ImageView mainIcon = findViewById(R.id.main_icon);
        TextView mainButton = findViewById(R.id.main_scene);
        TextView logInPage = findViewById(R.id.loginPage);

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
    }

    public static void goToMainPage (Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void goToLoginPage (Context context){
        Intent intent = new Intent(context, LoginPage.class);
        context.startActivity(intent);
    }
}