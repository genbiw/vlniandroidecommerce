package com.example.vlnie_commerce.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vlnie_commerce.model.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CouseViewHolder> {

    Context context;
    List<Course> courses;
    @NonNull
    @Override
    public CouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CouseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static final class CouseViewHolder extends RecyclerView.ViewHolder {
        public CouseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
