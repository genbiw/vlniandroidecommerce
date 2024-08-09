package com.example.vlnie_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DevicePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_page);

        ConstraintLayout deviceBg = findViewById(R.id.devicePageBg);
        ImageView deviceImage = findViewById(R.id.devicePageImage);
        TextView deviceName = findViewById(R.id.devicePageName);
        TextView devicePrice = findViewById(R.id.devicePagePrice);
        TextView deviceText= findViewById(R.id.devicePageText);

        deviceBg.setBackgroundColor(getIntent().getIntExtra("deviceBg", 0));
        deviceImage.setImageResource(getIntent().getIntExtra("deviceImage", 0));
        deviceName.setText(getIntent().getStringExtra("deviceName"));
        devicePrice.setText(getIntent().getStringExtra("devicePrice"));
        deviceText.setText(getIntent().getStringExtra("deviceText"));

        ImageView main_icon = findViewById(R.id.main_icon);
        TextView main_button = findViewById(R.id.main_scene);
        main_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainPage(DevicePage.this);
            }
        });

        main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainPage(DevicePage.this);
            }
        });

        TextView loginPage = findViewById(R.id.loginPage);
        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginPage(DevicePage.this);
            }
        });
    }

    public static void goToMainPage(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void goToLoginPage(Context context){
        Intent intent = new Intent(context, LoginPage.class);
        context.startActivity(intent);
    }
}