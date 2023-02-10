package com.example.fixneat.Viwes.ui.orders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fixneat.Model.Address;
import com.example.fixneat.R;
import com.example.fixneat.databinding.FragmentCreateOrderBinding;
import com.example.fixneat.databinding.FragmentHomeBinding;
import com.google.android.material.snackbar.Snackbar;


public class CreateOrderFragment extends Fragment {

    private FragmentCreateOrderBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //FragmentManager fragmentManager = getParentFragment() != null ? getParentFragment().getFragmentManager() : getFragmentManager();


        binding = FragmentCreateOrderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.AppointmentBTNSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                getData();
                Bundle bundle = new Bundle();


                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_menu, new HomeFragment())
                        .commit();
            }
        });

        return root;
    }

    private Address getData() {
        String city = binding.AppointmentETFirstName.getText().toString();
        String street = binding.AppointmentETLastName.getText().toString();

        Address address = new Address();
        address.setCity(city).setStreet(street);

        Log.d("Address", address.toString());
        return address;
    }

}