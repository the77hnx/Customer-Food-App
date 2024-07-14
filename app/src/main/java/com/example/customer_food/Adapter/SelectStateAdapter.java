// SelectStateAdapter.java
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

public class SelectStateAdapter extends RecyclerView.Adapter<SelectStateAdapter.ViewHolder> {

    private Context context;
    private List<String> states;
    private OnItemClickListener listener;

    public SelectStateAdapter(Context context, List<String> states) {
        this.context = context;
        this.states = states;
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
        String state = states.get(position);
        holder.stateTextView.setText(state);
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView stateTextView;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            stateTextView = itemView.findViewById(R.id.country_tv);
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
