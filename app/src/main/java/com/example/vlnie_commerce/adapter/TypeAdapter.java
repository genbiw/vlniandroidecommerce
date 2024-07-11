package com.example.vlnie_commerce.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vlnie_commerce.MainActivity;
import com.example.vlnie_commerce.R;
import com.example.vlnie_commerce.model.Type;

import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeViewHolder> {

    Context context;
    List<Type> types;
    int selectedPosition = -1;

    public TypeAdapter(Context context, List<Type> types) {
        this.context = context;
        this.types = types;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View typeItems = LayoutInflater.from(context).inflate(R.layout.type_item, parent, false);
        return new TypeViewHolder(typeItems);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeAdapter.TypeViewHolder holder, int position) {
        holder.typeTitle.setText(types.get(position).getTitle());

        if (selectedPosition == position) {
            holder.typeTitle.setTextColor(ContextCompat.getColor(context, R.color.name_of_app)); // Change to the desired color
        } else {
            holder.typeTitle.setTextColor(ContextCompat.getColor(context, R.color.black));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                MainActivity.showDevicesByType(types.get(position).getId());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    public void resetSelection() {
        selectedPosition = -1;
        notifyDataSetChanged();
    }

    public static final class TypeViewHolder extends RecyclerView.ViewHolder {

        TextView typeTitle;
        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);

            typeTitle = itemView.findViewById(R.id.typeTitle);
        }
    }
}
