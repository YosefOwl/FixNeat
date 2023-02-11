package com.example.fixneat.Interfaces;

import com.example.fixneat.Model.Address;

public interface OrderCallback {
    void openOrder(Address address, int adapterPosition);
}
