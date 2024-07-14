package com.example.customer_food.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customer_food.R;
import com.example.customer_food.Model.ShopItem;

import java.util.ArrayList;

public class PopularRestaurantsAdapter extends RecyclerView.Adapter<PopularRestaurantsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ShopItem> shopItemList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ShopItem item);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    private ArrayList<ShopItem> originalList;


    public PopularRestaurantsAdapter(Context context, ArrayList<ShopItem> shopItemList) {
        this.context = context;
        this.shopItemList = new ArrayList<>(shopItemList); // Initialize the original list
        this.originalList = new ArrayList<>(shopItemList); // Initialize the original list

    }

    public void filter(String searchText) {
        shopItemList.clear();
        if (searchText.isEmpty()) {
            shopItemList.addAll(originalList); // Restore original list if search text is empty
        } else {
            for (ShopItem item : originalList) {
                // Filter logic based on your requirements (e.g., restaurant name contains searchText)
                if (item.getRestaurantName().toLowerCase().contains(searchText.toLowerCase())) {
                    shopItemList.add(item);
                }
            }
        }
        notifyDataSetChanged(); // Notify adapter of data change
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.frag_shop_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShopItem currentItem = shopItemList.get(position);
        holder.restaurantName.setText(currentItem.getRestaurantName());
        holder.restaurantLocation.setText(currentItem.getRestaurantLocation());
        holder.restaurantValue.setText(currentItem.getRestaurantValue());
        holder.restaurantStatus.setText(currentItem.getRestaurantStatus());
    }

    @Override
    public int getItemCount() {
        return shopItemList.size();
    }

    public ShopItem getItem(int position) {
        if (position >= 0 && position < shopItemList.size()) {
            return shopItemList.get(position);
        }
        return null;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView restaurantName;
        public TextView restaurantLocation;
        public TextView restaurantValue;
        public TextView restaurantStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.resname);
            restaurantLocation = itemView.findViewById(R.id.placeres);
            restaurantValue = itemView.findViewById(R.id.valtv);
            restaurantStatus = itemView.findViewById(R.id.status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(shopItemList.get(position));
                        }
                    }
                }
            });
        }
    }


}