package com.example.fixneat.views.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fixneat.Adapters.OrderAdapter;
import com.example.fixneat.Interfaces.OrderCallback;
import com.example.fixneat.Model.Order;
import com.example.fixneat.R;
import com.example.fixneat.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public static final String NEW_ORDER = "NEW_ORDER";

    private FragmentHomeBinding binding;
    private OrderViewModel orderViewModel;
    private OrderAdapter orderAdapter;

    OrderCallback orderCallback = new OrderCallback() {
        @Override
        public void displayOrder(Order order, int adapterPosition) {
            DisplayOrderFragment fragment = new DisplayOrderFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(DisplayOrderFragment.ORDER_F_PARENT_DOF, order);
            fragment.setArguments(bundle);
            replaceFragment(fragment);
        }

        @Override
        public void makeCall(Order order, int adapterPosition) {

        }
    };

    public HomeFragment (){}

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.ordersRCV.setHasFixedSize(true);
        binding.ordersRCV.setLayoutManager(new LinearLayoutManager(this.getContext()));

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        if (getArguments() != null) {
            Order order = getArguments().getParcelable(NEW_ORDER);
            orderViewModel.insert(order);
        }

        orderViewModel.getOrders().observe(getViewLifecycleOwner(), observer);
        orderAdapter = new OrderAdapter();
        initButtons();

        return binding.getRoot();
    }

    private void initButtons() {
        binding.crateOrderFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new CreateOrderFragment());
            }
        });
    }


    private void replaceFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_content_menu, fragment).addToBackStack(null)
                .commit();
    }

    Observer<ArrayList<Order>> observer = new Observer<ArrayList<Order>>() {
        @Override
        public void onChanged(ArrayList<Order> orders) {

            if (orders == null) {
                binding.noOrdersTV.setVisibility(View.VISIBLE);
            }
            else if (orders.size() == 0){
                binding.noOrdersTV.setVisibility(View.VISIBLE);
            }

            else {
                binding.noOrdersTV.setVisibility(View.GONE);
                binding.ordersRCV.setVisibility(View.VISIBLE);

                orderAdapter.setOrderCallback(orderCallback);
                orderAdapter.setOrdersList(orders);
                binding.ordersRCV.setAdapter(orderAdapter);
                orderAdapter.notifyDataSetChanged();
            }
        }
    };
}