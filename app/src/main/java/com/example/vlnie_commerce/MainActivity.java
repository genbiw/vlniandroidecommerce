package com.example.vlnie_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.vlnie_commerce.adapter.CategoryAdapter;
import com.example.vlnie_commerce.adapter.TypeAdapter;
import com.example.vlnie_commerce.model.Category;
import com.example.vlnie_commerce.model.Type;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler;
    CategoryAdapter categoryAdapter;
    RecyclerView typeRecycler;
    TypeAdapter typeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "TV"));
        categoryList.add(new Category(2, "Phone"));
        categoryList.add(new Category(3, "Laptop"));
        categoryList.add(new Category(4, "Tablet"));

        setCategoryRecycler(categoryList);

        List<Type> typeList = new ArrayList<>();
        typeList.add(new Type(1, "Apple"));
        typeList.add(new Type(2, "Samsung"));
        typeList.add(new Type(3, "Huawei"));
        typeList.add(new Type(4, "Sony"));
        typeList.add(new Type(5, "LG"));
        typeList.add(new Type(6, "Xiaomi"));
        typeList.add(new Type(7, "Vivo"));
        typeList.add(new Type(8, "Oppo"));
        typeList.add(new Type(9, "HP"));
        typeList.add(new Type(10, "Lenovo"));



        setTypeRecycler(typeList);


    }

    private void setTypeRecycler(List<Type> typeList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        typeRecycler = findViewById(R.id.typeRecycler);
        typeRecycler.setLayoutManager(layoutManager);

        typeAdapter = new TypeAdapter(this, typeList);
        typeRecycler.setAdapter(typeAdapter);


    }

    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }
}