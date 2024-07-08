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

import com.example.vlnie_commerce.DevicePage;
import com.example.vlnie_commerce.R;
import com.example.vlnie_commerce.model.Device;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {

    Context context;
    List<Device> devices;

    public DeviceAdapter(Context context, List<Device> devices) {
        this.context = context;
        this.devices = devices;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View deviceItems = LayoutInflater.from(context).inflate(R.layout.device_item, parent, false);
        return new DeviceAdapter.DeviceViewHolder(deviceItems);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        String colorString = devices.get(position).getColor();
        Log.d("CourseAdapter", "Color: " + colorString);

        try {
            holder.deviceBg.setBackgroundColor(Color.parseColor(colorString));
        } catch (IllegalArgumentException e) {
            Log.e("DeviceAdapter", "Invalid color format: " + colorString, e);
            // Set a default color in case of error
            holder.deviceBg.setBackgroundColor(Color.BLACK);
        }

        int imageId = context.getResources().getIdentifier(devices.get(position).getImg(), "drawable", context.getPackageName());
        holder.deviceImage.setImageResource(imageId);

        String name = devices.get(position).getName();
        Integer raiting = devices.get(position).getRaiting();
        String info = devices.get(position).getInfo();

        holder.deviceName.setText(name);
        holder.deviceRating.setText(String.valueOf(raiting));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DevicePage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                            (Activity) context,
                            new Pair<View, String>(holder.deviceImage, "deviceImage")
                        );

                intent.putExtra("deviceBg", Color.parseColor(colorString));
                intent.putExtra("deviceImage", imageId);
                intent.putExtra("deviceName", name);
                intent.putExtra("deviceText", info);

                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }



    public static final class DeviceViewHolder extends RecyclerView.ViewHolder {
        LinearLayout deviceBg;
        ImageView deviceImage;
        TextView deviceName;
        TextView deviceRating;

        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);

            deviceBg = itemView.findViewById(R.id.deviceBg);
            deviceImage = itemView.findViewById(R.id.deviceImage);
            deviceName = itemView.findViewById(R.id.deviceName);
            deviceRating = itemView.findViewById(R.id.rating);
        }
    }
}
