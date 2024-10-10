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

import com.google.android.material.textfield.TextInputEditText;

import com.example.vlnie_commerce.http.Login; // Import your Login class
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.util.Log;  // Add this import to use the Log class

public class LoginPage extends AppCompatActivity {

    private TextInputEditText emailInput, passwordInput;
    private ExecutorService executorService; // Declare the ExecutorService

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        executorService = Executors.newSingleThreadExecutor(); // Initialize ExecutorService

        ImageView main_icon = findViewById(R.id.main_icon);
        TextView main_button = findViewById(R.id.main_scene);
        TextView singUpLink = findViewById(R.id.singuplink);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);

        Button buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin(LoginPage.this);
            }
        });

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

    public void showCollectedData (Context context){
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        String message = "Email: " + email + "\n" +
                "Password: " + password;

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown(); // Shutdown the executor when the activity is destroyed
    }

    private void attemptLogin(final Context context) {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        // Log the input values
        Log.d("LoginPage", "Attempting login with email: " + email + " and password: " + password);


        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Submit the task to the executor service
        Future<String> future = executorService.submit(new LoginTask(email, password));

        try {
            // Retrieve the result of the background task
            String result = future.get(); // This blocks the UI thread until the result is available

            if (result != null) {
                // Log the successful result
                Log.d("LoginPage", "Login successful: " + result);

                Toast.makeText(context, "Login successful: " + result, Toast.LENGTH_LONG).show();
                goToMainPage(context);
            } else {
                Toast.makeText(context, "Login failed. Please try again.", Toast.LENGTH_LONG).show();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(context, "An error occurred", Toast.LENGTH_LONG).show();
        }
    }

    // The background task for logging in
    private static class LoginTask implements Callable<String> {
        private final String email;
        private final String password;

        LoginTask(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        public String call() {
            Login login = new Login();
            // Replace "your-api-url" with the actual URL of your API endpoint
            return login.loginUser("http://10.38.30.117:5000/api/user/login", email, password);
        }
    }

}