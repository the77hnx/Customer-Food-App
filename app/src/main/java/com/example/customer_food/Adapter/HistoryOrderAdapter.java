package com.example.customer_food.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customer_food.DetailedOrderActivity;
import com.example.customer_food.Model.Order;
import com.example.customer_food.R;

import java.util.List;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> orders;

    public HistoryOrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.frag_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.textViewRestaurantName.setText("" + order.getRestaurantName());
        holder.textViewOrderPrice.setText("" + order.getOrderPrice() + "");
        holder.textViewDeliveryPrice.setText("" + order.getDeliveryPrice() + "");
        holder.textViewNumberOfDelivery.setText("رقم الطلب : " + order.getOrderNumber());
        holder.textViewDateOfDelivery.setText("تاريخ الطلب : " + order.getDateOfDelivery());
        holder.textViewDeliveryStatus.setText("حالة الطلب : " + order.getDeliveryStatus());

        holder.buttonMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedOrderActivity.class);
                intent.putExtra("orderDetails", order);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView textViewRestaurantName, textViewOrderPrice, textViewDeliveryPrice, textViewNumberOfDelivery, textViewDateOfDelivery, textViewDeliveryStatus;
        Button buttonMoreInfo;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewRestaurantName = itemView.findViewById(R.id.textViewRestaurantName);
            textViewOrderPrice = itemView.findViewById(R.id.textViewOrderPrice);
            textViewDeliveryPrice = itemView.findViewById(R.id.textViewDeliveryPrice);
            textViewNumberOfDelivery = itemView.findViewById(R.id.textViewNumberOfDelivery);
            textViewDateOfDelivery = itemView.findViewById(R.id.textViewDateOfDelivery);
            textViewDeliveryStatus = itemView.findViewById(R.id.textViewDeliveryStatus);
            buttonMoreInfo = itemView.findViewById(R.id.buttonMoreInfo);
        }
    }
}
