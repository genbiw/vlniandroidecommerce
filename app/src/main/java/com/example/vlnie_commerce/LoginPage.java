package com.example.vlnie_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        ImageView main_icon = findViewById(R.id.main_icon);
        TextView main_button = findViewById(R.id.main_scene);
        TextView singUpLink = findViewById(R.id.singuplink);

        main_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainPage(LoginPage.this);
            }
        });

        main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainPage(LoginPage.this);
            }
        });

        singUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegistrationPage(LoginPage.this);
            }
        });
    }

    public static void goToMainPage (Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void goToRegistrationPage (Context context){
        Intent intent = new Intent(context, Registration.class);
        context.startActivity(intent);
    }
}