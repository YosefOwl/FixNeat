package com.example.fixneat.Repository;

import com.example.fixneat.Model.Order;

import java.util.ArrayList;

public interface OrderDataCallback {

    void uploadFinish(ArrayList<Order> mldOrdersList);
}
