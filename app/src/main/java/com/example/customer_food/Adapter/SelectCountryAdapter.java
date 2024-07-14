package com.example.customer_food.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customer_food.R;

import java.util.List;

public class SelectCountryAdapter extends RecyclerView.Adapter<SelectCountryAdapter.ViewHolder> {

    private Context context;
    private List<String> countries;
    private OnItemClickListener listener;

    public SelectCountryAdapter(Context context, List<String> countries) {
        this.context = context;
        this.countries = countries;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.frag_select_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String country = countries.get(position);
        if (holder.countryTextView != null) {
            holder.countryTextView.setText(country);
        }
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryTextView = itemView.findViewById(R.id.country_tv);
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
