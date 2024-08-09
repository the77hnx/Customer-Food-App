package com.example.customer_food.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customer_food.Model.FoodItem;
import com.example.customer_food.R;

import java.util.List;

public class ItemDetailsAdpter extends RecyclerView.Adapter<ItemDetailsAdpter.ViewHolder> {

    private List<FoodItem> itemList;
    private Context context;

    public ItemDetailsAdpter(Context context, List<FoodItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_order_detailed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem item = itemList.get(position);
        holder.titleTextView.setText(item.getName());
        holder.PriceTextView.setText(item.getName());
        holder.imageView.setImageResource(item.getImageResource());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView PriceTextView;

        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.name_user);
            PriceTextView = itemView.findViewById(R.id.place_user);
            imageView = itemView.findViewById(R.id.shapeableImageView);
        }
    }
}


