package com.example.customer_food.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customer_food.Model.CategoriesItem;
import com.example.customer_food.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CategoriesItem> categoryList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    private ArrayList<CategoriesItem> originalList;
    public CategoriesAdapter(Context context, ArrayList<CategoriesItem> categoryList) {
        this.context = context;
        this.categoryList = new ArrayList<>(categoryList); // Initialize the original list
        this.originalList = new ArrayList<>(categoryList); // Initialize the original list

    }
    public void filter(String searchText) {
        categoryList.clear();
        if (searchText.isEmpty()) {
            categoryList.addAll(originalList); // Restore original list if search text is empty
        } else {
            for (CategoriesItem item : originalList) {
                // Filter logic based on your requirements (e.g., category name contains searchText)
                if (item.getCategoryName().toLowerCase().contains(searchText.toLowerCase())) {
                    categoryList.add(item);
                }
            }
        }
        notifyDataSetChanged(); // Notify adapter of data change
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.frag_cat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoriesItem currentItem = categoryList.get(position);
        holder.categoryName.setText(currentItem.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.card_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
