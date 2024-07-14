// SelectMunicipalAdapter.java
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

public class SelectMunicipalAdapter extends RecyclerView.Adapter<SelectMunicipalAdapter.ViewHolder> {

    private Context context;
    private List<String> municipalities;
    private OnItemClickListener listener;

    public SelectMunicipalAdapter(Context context, List<String> municipalities) {
        this.context = context;
        this.municipalities = municipalities;
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
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String municipal = municipalities.get(position);
        holder.municipalTextView.setText(municipal);
    }

    @Override
    public int getItemCount() {
        return municipalities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView municipalTextView;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            municipalTextView = itemView.findViewById(R.id.country_tv);
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
