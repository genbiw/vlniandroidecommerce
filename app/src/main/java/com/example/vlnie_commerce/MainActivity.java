package com.example.vlnie_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vlnie_commerce.adapter.TypeAdapter;
import com.example.vlnie_commerce.adapter.DeviceAdapter;
import com.example.vlnie_commerce.adapter.BrandAdapter;
import com.example.vlnie_commerce.model.Brand;
import com.example.vlnie_commerce.model.Device;
import com.example.vlnie_commerce.model.Type;

import org.infobip.mobile.messaging.MobileMessaging;
import org.infobip.mobile.messaging.mobileapi.MobileMessagingError;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView typeRecycler, brandRecycler, deviceRecycler;
    TypeAdapter typeAdapter;
    BrandAdapter brandAdapter;
    static DeviceAdapter deviceAdapter;
    static List<Device> deviceList = new ArrayList<>();
    static List<Device> fullDevicesList = new ArrayList<>();
    static int selectedType = -1;  // -1 indicates no type selected
    static int selectedBrand = -1; // -1 indicates no brand selected
    static boolean isDeviceListInitialized = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Type> typeList = new ArrayList<>();
        typeList.add(new Type(1, "TV"));
        typeList.add(new Type(2, "Phone"));
        typeList.add(new Type(3, "Laptop"));
        typeList.add(new Type(4, "Tablet"));

        setTypeRecycler(typeList);

        List<Brand> brandList = new ArrayList<>();
        brandList.add(new Brand(1, "Apple"));
        brandList.add(new Brand(2, "Samsung"));
        brandList.add(new Brand(3, "Huawei"));
        brandList.add(new Brand(4, "Sony"));
        brandList.add(new Brand(5, "LG"));
        brandList.add(new Brand(6, "Xiaomi"));
        brandList.add(new Brand(7, "Vivo"));
        brandList.add(new Brand(8, "Oppo"));
        brandList.add(new Brand(9, "HP"));
        brandList.add(new Brand(10, "Lenovo"));

        setBrandRecycler(brandList);

        if (!isDeviceListInitialized) {
            initializeDeviceList();
            isDeviceListInitialized = true;
        }

        setDeviceRecycler(deviceList);

        ImageView resetFilterImageView = findViewById(R.id.resetFilterImageView);
        resetFilterImageView.setOnClickListener(v -> resetFiltering());

        //Retrieve the token to check if user is authorized

        SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        Boolean isAuth = sharedPreferences.getBoolean("isAuth", false);
        String token = sharedPreferences.getString("token", null);  // Retrieve the token, null if not set

        if (token != null) {
            // User is authenticated, you can use the token for requests
        } else {
            // Token is not available, user might not be logged in
        }

        TextView loginPage = findViewById(R.id.loginPage);
        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mainPage", "isAuth = " + isAuth);
                Log.d("mainPage", "token = " + token);
                if(!isAuth){
                    goToLoginPage(MainActivity.this);
                }else{
                    goToUserPage(MainActivity.this);
                }

            }
        });

        new MobileMessaging.Builder(getApplication()).build();
    }

    private void initializeDeviceList() {
        deviceList.add(new Device(1, 2, 1, 9, "iphone15pro", "iPhone 15 Pro", "2000", "#424345", "Model year: 2023. \nDisplay diagonal, inch: 6.1. \nDisplay resolution: 1080x2640"));
        deviceList.add(new Device(2, 2, 1, 7, "iphone14", "iPhone 14", "1000", "#424345", "Model year: 2021. \nDisplay diagonal, inch: 6.1. \nDisplay resolution: 1080x2640"));
        deviceList.add(new Device(3, 1, 1, 7, "appletv", "Apple TV", "500", "#424345", "Model year: 2021"));
        deviceList.add(new Device(4, 3, 1, 7, "imac", "Apple 2023 iMac MQRQ3HN/A AIO Desktop (Apple M3/ 8 GB/ 256 GB SSD/ macOS/ 10 Core GPU)", "5000", "#424345", "Model year: 2023. \nThickness: 147mm \nWeight: 4.43 kg"));
        deviceList.add(new Device(5, 3, 1, 7, "apple_macbook_pro_14_2023_laptop", "iPhone 14", "1000", "#424345", "Model year: 2021. \nDisplay diagonal, inch: 6.1. \nDisplay resolution: 1080x2640"));
        deviceList.add(new Device(6, 3, 3, 7, "huawei_matebook_14", "iPhone 14", "1000", "#424345", "Model year: 2021. \nDisplay diagonal, inch: 6.1. \nDisplay resolution: 1080x2640"));
        deviceList.add(new Device(7, 2, 3, 7, "huawei_p60", "iPhone 14", "1000", "#424345", "Model year: 2021. \nDisplay diagonal, inch: 6.1. \nDisplay resolution: 1080x2640"));
        deviceList.add(new Device(8, 3, 2, 7, "samsung_galaxy_book_3_ultra", "iPhone 14", "1000", "#424345", "Model year: 2021. \nDisplay diagonal, inch: 6.1. \nDisplay resolution: 1080x2640"));
        deviceList.add(new Device(9, 2, 2, 7, "samsung_galaxy_note_21", "iPhone 14", "1000", "#424345", "Model year: 2021. \nDisplay diagonal, inch: 6.1. \nDisplay resolution: 1080x2640"));
        deviceList.add(new Device(10, 1, 4, 7, "sony_kd_85x80lu", "iPhone 14", "1000", "#9FA52D", "Model year: 2021. \nDisplay diagonal, inch: 6.1. \nDisplay resolution: 1080x2640"));

        fullDevicesList.addAll(deviceList);
    };

    private void setDeviceRecycler(List<Device> deviceList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        deviceRecycler = findViewById(R.id.deviceRecycler);
        deviceRecycler.setLayoutManager(layoutManager);

        deviceAdapter = new DeviceAdapter(this, deviceList);
        deviceRecycler.setAdapter(deviceAdapter);
    }

    private void setTypeRecycler(List<Type> typeList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        typeRecycler = findViewById(R.id.typeRecycler);
        typeRecycler.setLayoutManager(layoutManager);

        typeAdapter = new TypeAdapter(this, typeList);
        typeRecycler.setAdapter(typeAdapter);


    }

    private void setBrandRecycler(List<Brand> brandList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        brandRecycler = findViewById(R.id.brandRecycler);
        brandRecycler.setLayoutManager(layoutManager);

        brandAdapter = new BrandAdapter(this, brandList);
        brandRecycler.setAdapter(brandAdapter);
    }

    public static void showDevicesByType(int type){
        selectedType = type;  // Update the selected type
        filterDevices();
    }

    public static void showDeviceByBrand(int brand){
        selectedBrand = brand;  // Update the selected brand
        filterDevices();
    }

    private static void filterDevices() {
        deviceList.clear();
        deviceList.addAll(fullDevicesList);

        List<Device> filteredDevices = new ArrayList<>();

        for (Device c : fullDevicesList) {
            if ((selectedType == -1 || c.getType() == selectedType) &&
                    (selectedBrand == -1 || c.getBrand() == selectedBrand)) {
                filteredDevices.add(c);
            }
        }

        deviceList.clear();
        deviceList.addAll(filteredDevices);

        deviceAdapter.notifyDataSetChanged();
    }

    private void resetFiltering(){
        selectedType = -1;  // Reset the selected type
        selectedBrand = -1; // Reset the selected brand
        deviceList.clear();
        deviceList.addAll(fullDevicesList);
        deviceAdapter.notifyDataSetChanged();
        typeAdapter.resetSelection();
        brandAdapter.resetSelection();
    }

    private static void goToLoginPage (Context context) {
        Intent intent = new Intent(context, LoginPage.class);
        context.startActivity(intent);
    }

    private static void goToUserPage (Context context){
        Intent intent = new Intent(context, user.class);
        context.startActivity(intent);
    }
}