package com.example.fixneat.views.ui.orders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fixneat.Model.Order;
import com.example.fixneat.Repository.OrderDataCallback;
import com.example.fixneat.Repository.OrderRepo;

import java.util.ArrayList;

public class OrderViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Order>> mOrders;

    private OrderRepo orderRepository;


    OrderDataCallback orderDataCallback = new OrderDataCallback() {
        @Override
        public void uploadFinish(ArrayList<Order> orders) {
            mOrders.setValue(orders);
        }
    };

    public OrderViewModel() {
        mOrders = new MutableLiveData<>();
        orderRepository = OrderRepo.getInstance();
        orderRepository.setCallback(orderDataCallback);
        orderRepository.getOrders();
    }

    public void insert(Order order) {
        orderRepository.insertOrder(order);
    }

    public void update(Order order, boolean isStatusOnly) {
        orderRepository.updateOrder(order, isStatusOnly);
    }

    public void delete() {
        orderRepository.deleteOrder();
    }

    public LiveData<ArrayList<Order>> getOrders() {
        return mOrders;
    }

}