package com.example.fixneat.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixneat.Interfaces.OrderCallback;
import com.example.fixneat.Model.Order;
import com.example.fixneat.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;



public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private ArrayList<Order> ordersList;
    private OrderCallback orderCallback;

    public OrderAdapter () {
        this.ordersList = new ArrayList<>();
    }

    public void setOrdersList(ArrayList<Order> orders) {

        if (ordersList != null) {
            this.ordersList.clear();
            this.ordersList.addAll(orders);
            notifyDataSetChanged();
        }
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
        Order order = getItem(position);

        String firstName = "";
        String lastName = "";
        if (order.getClient() != null) {
            firstName = order.getClient().getFirstName() != null ? order.getClient().getFirstName():"";
            lastName = order.getClient().getLastName() != null ? order.getClient().getLastName():"";
        }

        String note = (order.getNote() != null) ? order.getNote() :"empty";
        String dateF = (order.getMeasureVisitD_T() != null)? order.getMeasureVisitD_T().toString():"";
        String date = (order.getInstallVisitD_T() != null )? order.getMeasureVisitD_T().toString(): dateF;

        holder.clientName.setText("Client Name: " + firstName + " " + lastName + "");
        holder.date.setText("Next Visit D&T: " + date + "");
        holder.note.setText("Note: " + note + "");
        holder.status.setText("Status: " + order.getCurrentStatus() + "");
        holder.img.setImageResource(R.drawable.splash_img);
    }


    @Override
    public int getItemCount() {
        return ordersList == null ? 0 : ordersList.size();
    }

    private Order getItem(int position) {
        return ordersList.get(position);
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder {

        private final MaterialTextView clientName;
        private final MaterialTextView date;
        private final MaterialTextView status;
        private final MaterialTextView note;
        private final AppCompatImageView img;
        private final MaterialTextView call;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            clientName = itemView.findViewById(R.id.order_item_client_name_TV);
            status = itemView.findViewById(R.id.order_item_status_TV);
            note = itemView.findViewById(R.id.order_item_note_TV);
            date = itemView.findViewById(R.id.order_item_date_TV);

            call =  itemView.findViewById(R.id.order_call_TV);

            img = itemView.findViewById(R.id.order_item_IMGV);

            call.setOnClickListener(view -> {
                // not implement yet
                orderCallback.makeCall(getItem(getAdapterPosition()), getAdapterPosition());
            });
            itemView.setOnClickListener(view -> {
                orderCallback.displayOrder(getItem(getAdapterPosition()), getAdapterPosition());
            });

        }
    }
}

