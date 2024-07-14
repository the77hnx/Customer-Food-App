package com.example.customer_food.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customer_food.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SelectCityAdapter extends RecyclerView.Adapter<SelectCityAdapter.ViewHolder> {

    private ArrayList<String> cities;
    private ArrayList<String> citiesFull; // Original list to keep all cities
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cityName;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            cityName = itemView.findViewById(R.id.country_tv);

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

    public SelectCityAdapter(ArrayList<String> cities) {
        this.cities = cities;
        this.citiesFull = new ArrayList<>(cities); // Make a copy of cities for filtering
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_select_item, parent, false);
        return new ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String currentCity = cities.get(position);
        holder.cityName.setText(currentCity);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    // Method to filter cities based on search query
    public void filter(String query) {
        query = query.toLowerCase(Locale.getDefault());
        cities.clear();
        if (query.length() == 0) {
            cities.addAll(citiesFull);
        } else {
            for (String city : citiesFull) {
                if (city.toLowerCase(Locale.getDefault()).contains(query)) {
                    cities.add(city);
                }
            }
        }
        notifyDataSetChanged();
    }
}
