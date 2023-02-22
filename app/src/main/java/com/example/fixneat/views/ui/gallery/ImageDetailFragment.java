package com.example.fixneat.views.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.fixneat.R;
import com.example.fixneat.Utils.ImageLoader;
import com.example.fixneat.databinding.FragmentImageDetailBinding;
import com.example.fixneat.databinding.NavHeaderMenuBinding;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageDetailFragment extends Fragment {

    public static final String IMAGE_PATH = "IMAGE_PATH";

    String imagePath;
    private FragmentImageDetailBinding binding;
    private ScaleGestureDetector scaleGestureDetector;

    // on below line we are defining our scale factor.
    private float mScaleFactor = 1.0f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentImageDetailBinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            imagePath = getArguments().getString(IMAGE_PATH);
        }

        // initializing scale gesture detector for zoom in and out for our image.
        scaleGestureDetector = new ScaleGestureDetector(this.getContext(), new ScaleListener());

        initButtons();
        updateUI();
        return binding.getRoot();
    }

    private void updateUI() {

        ImageLoader.getInstance().load(imagePath, binding.detailsImageIMGV);
    }

    private void initButtons() {
        binding.closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
        binding.nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
            }
        });
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        // on below line we are creating a class for our scale
        // listener and  extending it with gesture listener.
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {

            // inside on scale method we are setting scale
            // for our image in our image view.
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));

            // on below line we are setting
            // scale x and scale y to our image view.
            binding.detailsImageIMGV.setScaleX(mScaleFactor);
            binding.detailsImageIMGV.setScaleY(mScaleFactor);
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return super.onScaleBegin(detector);
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            super.onScaleEnd(detector);
        }

        public ScaleListener() {
            super();
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            // inside on touch event method we are calling on
            // touch event method and passing our motion event to it.
            scaleGestureDetector.onTouchEvent(motionEvent);
            return true;
        }

    }

}