package com.example.fixneat.Viwes.ui.orders;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fixneat.databinding.FragmentDisplayOrderBinding;

public class DisplayOrderFragment extends Fragment {

    private FragmentDisplayOrderBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDisplayOrderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }



}