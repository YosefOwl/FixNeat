package com.example.fixneat.Viwes.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixneat.Model.Address;
import com.example.fixneat.R;
import com.example.fixneat.Interfaces.OrderCallback;
import com.example.fixneat.adapters.OrderAdapter;
import com.example.fixneat.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private OrderViewModel homeViewModel;

    OrderCallback orderCallback = (address, position) -> {
        replaceFragment(new DisplayOrderFragment());
    };

    private void replaceFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_content_menu, fragment)
                .commit();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.orders;
        homeViewModel.getList().observe(getViewLifecycleOwner(),observer);

        binding.addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new CreateOrderFragment());
            }
        });

        initView();

        return root;
    }

    private void initView() {
        ArrayList addresses = homeViewModel.getList().getValue();
        if (addresses == null)
            return;
        orderAdapter = new OrderAdapter(this.getContext(), addresses);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(orderAdapter);
        orderAdapter.setOrderCallback(orderCallback);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    Observer<ArrayList<Address>> observer = new Observer<ArrayList<Address>>() {
        @Override
        public void onChanged(ArrayList<Address> addresses) {
            orderAdapter.notifyDataSetChanged();
        }
    };

}