package com.example.fixneat.Viwes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fixneat.Model.Address;
import com.example.fixneat.R;
import com.example.fixneat.Viwes.Interfaces.OrderCallback;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private ArrayList<Address> addresses;
    OrderCallback orderCallback;

    public OrderAdapter(Context context, ArrayList<Address> addresses) {
        this.context = context;
        this.addresses = addresses;
    }


    public void setOrderCallback(OrderCallback orderCallback){
        this.orderCallback = orderCallback;
    }
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);

        return new OrderViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {

        Address address = getItem(position);

        holder.city.setText("city : " + address.getCity());
        holder.street.setText("street : " + address.getStreet() + "");
        holder.img.setImageResource(R.drawable.splash_img);
        //holder.imageButton.setImageResource(R.drawable.ic_menu_camera);
    }


    @Override
    public int getItemCount() {
        return addresses == null ? 0 : addresses.size();
    }


    private Address getItem(int position) {
        return addresses.get(position);
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        private final MaterialTextView city;
        private final MaterialTextView street;
        private final AppCompatImageView img;
        //private final ImageButton imageButton;
        public OrderViewHolder(@NonNull View itemView) {

            super(itemView);
            city = itemView.findViewById(R.id.city);
            street = itemView.findViewById(R.id.street);
            img = itemView.findViewById(R.id.img);

            itemView.setOnClickListener(view -> {
                orderCallback.openOrder(getItem(getAdapterPosition()), getAdapterPosition());
            });

            // todo itemView.setOnLongClickListener();
        }
    }
}