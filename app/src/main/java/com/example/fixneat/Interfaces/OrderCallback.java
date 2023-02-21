package com.example.fixneat.Interfaces;

import com.example.fixneat.Model.Order;

public interface OrderCallback {

    void displayOrder(Order order, int adapterPosition); // from home
    void makeCall(Order order, int adapterPosition);
}
