package com.example.vlnie_commerce.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vlnie_commerce.CoursePage;
import com.example.vlnie_commerce.R;
import com.example.vlnie_commerce.model.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    Context context;
    List<Course> courses;

    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItems = LayoutInflater.from(context).inflate(R.layout.course_item, parent, false);
        return new CourseAdapter.CourseViewHolder(courseItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        String colorString = courses.get(position).getColor();
        Log.d("CourseAdapter", "Color: " + colorString);

        try {
            holder.courseBg.setBackgroundColor(Color.parseColor(colorString));
        } catch (IllegalArgumentException e) {
            Log.e("CourseAdapter", "Invalid color format: " + colorString, e);
            // Set a default color in case of error
            holder.courseBg.setBackgroundColor(Color.BLACK);
        }

        int imageId = context.getResources().getIdentifier(courses.get(position).getImg(), "drawable", context.getPackageName());
        holder.courseImage.setImageResource(imageId);

        String title = courses.get(position).getTitle();
        String date = courses.get(position).getDate();
        String level = courses.get(position).getLevel();
        String text = courses.get(position).getText();

        holder.courseTitle.setText(title);
        holder.courseDate.setText(date);
        holder.courseLevel.setText(level);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CoursePage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                            (Activity) context,
                            new Pair<View, String>(holder.courseImage, "courseImage")
                        );

                intent.putExtra("courseBg", Color.parseColor(colorString));
                intent.putExtra("courseImage", imageId);
                intent.putExtra("courseTitle", title);
                intent.putExtra("courseDate", date);
                intent.putExtra("courseLevel", level);
                intent.putExtra("courseText", text);

                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }



    public static final class CourseViewHolder extends RecyclerView.ViewHolder {
        LinearLayout courseBg;
        ImageView courseImage;
        TextView courseTitle, courseDate, courseLevel;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            courseBg = itemView.findViewById(R.id.courseBg);
            courseImage = itemView.findViewById(R.id.courseImage);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseDate = itemView.findViewById(R.id.courseDate);
            courseLevel = itemView.findViewById(R.id.courseLevel);
        }
    }
}
