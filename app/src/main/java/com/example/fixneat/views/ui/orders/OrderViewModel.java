package com.example.fixneat.views.ui.orders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fixneat.Model.Order;
import com.example.fixneat.Repository.DataCallback;
import com.example.fixneat.Repository.OrderRepo;

import java.util.ArrayList;

public class OrderViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Order>> mOrders;

    private OrderRepo repository;


    DataCallback dataCallback = new DataCallback() {
        @Override
        public void uploadFinish(ArrayList<Order> orders) {
            mOrders.setValue(orders);
        }
    };

    public OrderViewModel() {
        mOrders = new MutableLiveData<>();
        repository = OrderRepo.getInstance();
        repository.setCallback(dataCallback);
        repository.getOrders();
    }

    public void insert(Order order) {
        repository.insertOrder(order);
    }

    public void update(Order order, boolean isStatusOnly) {
        repository.updateOrder(order, isStatusOnly);
    }

    public void delete() {
        repository.deleteOrder();
    }

    public LiveData<ArrayList<Order>> getOrders() {
        return mOrders;
    }

}