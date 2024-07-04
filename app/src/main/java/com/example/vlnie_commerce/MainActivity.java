package com.example.vlnie_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.vlnie_commerce.adapter.CategoryAdapter;
import com.example.vlnie_commerce.adapter.CourseAdapter;
import com.example.vlnie_commerce.adapter.TypeAdapter;
import com.example.vlnie_commerce.model.Category;
import com.example.vlnie_commerce.model.Course;
import com.example.vlnie_commerce.model.Type;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, typeRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    TypeAdapter typeAdapter;
    CourseAdapter courseAdapter;
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

        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course(1, "java", "Profession Java\ndeveloper", "1 January", "beginner", "#424345", "The Java training program is designed for beginners in this area. For the program you will study the construction of graphic applications for PCs, the development of web sites based on Java Spring Boot, study the construction of full -fledged android applications and perfectly study the Java language itself!"));
        courseList.add(new Course(2, "python", "Profession Python\ndeveloper", "10 January", "beginner", "#9FA52D", "The Python training program is tailored for newcomers in this field. During the program, you will explore creating graphical applications for desktops, delve into web development using frameworks like Django, learn to develop robust Android applications, and gain a comprehensive understanding of the Python language itself!"));

        setCourseRecycler(courseList);

    }

    private void setCourseRecycler(List<Course> courseList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);
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