package com.example.fixneat.Viwes.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixneat.Interfaces.GalleryCallback;
import com.example.fixneat.Model.Address;
import com.example.fixneat.R;
import com.example.fixneat.Viwes.ui.orders.DisplayOrderFragment;
import com.example.fixneat.Viwes.ui.orders.OrderViewModel;
import com.example.fixneat.adapters.GalleryAdapter;
import com.example.fixneat.adapters.OrderAdapter;
import com.example.fixneat.databinding.FragmentGalleryBinding;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private RecyclerView recyclerView;
    private GalleryAdapter galleryAdapter;
    private GalleryViewModel galleryViewModel;


    GalleryCallback galleryCallback = (imageView, position) -> {
        replaceFragment(new ImageDetailFragment());
    };
    private void replaceFragment(Fragment fragment) {
//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.nav_host_fragment_content_menu, fragment)
//                .commit();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        galleryViewModel.setContext(this.getContext());
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.idRVImages;

        galleryViewModel.getImagePaths().observe(getViewLifecycleOwner(),observer);

        //final TextView textView = binding.textGallery;
        //galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        initView();
        return root;
    }


    private void initView() {
        ArrayList imagePaths = galleryViewModel.getImagePaths().getValue();

        if (imagePaths == null)
            return;

        // in this method we are preparing our recycler view.
        // on below line we are initializing our adapter class.
        galleryAdapter = new GalleryAdapter(this.getContext(), imagePaths);

        // on below line we are creating a new grid layout manager.
        GridLayoutManager manager = new GridLayoutManager(this.getContext(), 4);

        // on below line we are setting layout
        // manager and adapter to our recycler view.
        recyclerView.setLayoutManager(manager);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(galleryAdapter);

        galleryAdapter.setGalleryCallback(galleryCallback);
    }

    Observer<ArrayList<String>> observer = new Observer<ArrayList<String>>() {
        @Override
        public void onChanged(ArrayList<String> imagePaths) {
            galleryAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}