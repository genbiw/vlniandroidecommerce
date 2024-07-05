package com.example.vlnie_commerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vlnie_commerce.MainActivity;
import com.example.vlnie_commerce.R;
import com.example.vlnie_commerce.model.Type;

import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeViewHolder> {

    Context context;
    List<Type> types;

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.showDevicesByType(types.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    public static final class TypeViewHolder extends RecyclerView.ViewHolder {

        TextView typeTitle;
        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);

            typeTitle = itemView.findViewById(R.id.typeTitle);
        }
    }
}
