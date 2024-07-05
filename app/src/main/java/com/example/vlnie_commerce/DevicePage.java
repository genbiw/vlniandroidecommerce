package com.example.vlnie_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
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
    }
}