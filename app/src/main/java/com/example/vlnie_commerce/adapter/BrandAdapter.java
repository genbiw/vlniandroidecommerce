package com.example.vlnie_commerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vlnie_commerce.MainActivity;
import com.example.vlnie_commerce.R;
import com.example.vlnie_commerce.model.Brand;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandViewHolder> {

    Context context;
    List<Brand> brands;
    int selectedPosition = -1;

    public BrandAdapter(Context context, List<Brand> brands) {
        this.context = context;
        this.brands = brands;
    }

    @NonNull
    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View brandItems = LayoutInflater.from(context).inflate(R.layout.brand_item, parent, false);
        return new BrandViewHolder(brandItems);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {
        holder.brandTitle.setText(brands.get(position).getTitle());

        if (selectedPosition == position) {
            holder.brandTitle.setTextColor(ContextCompat.getColor(context, R.color.name_of_app)); // Change to the desired color
        } else {
            holder.brandTitle.setTextColor(ContextCompat.getColor(context, R.color.black));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                MainActivity.showDeviceByBrand(brands.get(position).getId());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    public void resetSelection() {
        selectedPosition = -1;
        notifyDataSetChanged();
    }

    public static final class BrandViewHolder extends RecyclerView.ViewHolder {

        TextView brandTitle;

        public BrandViewHolder(@NonNull View itemView) {
            super(itemView);
            brandTitle = itemView.findViewById(R.id.brandTitle);
        }
    }
}
