package com.example.fixneat.Viwes.ui.gallery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fixneat.Interfaces.GalleryCallback;
import com.example.fixneat.R;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageDetailFragment extends Fragment {

    // creating a string variable, image view variable
    // and a variable for our scale gesture detector class.
    String imgPath;
    private ImageView imageView;
    private ScaleGestureDetector scaleGestureDetector;
    // on below line we are defining our scale factor.
    private float mScaleFactor = 1.0f;

    private GalleryCallback galleryCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_image_detail, container, false);
        // on below line getting data which we have passed from our adapter class.
        imgPath = getIntent().getStringExtra("imgPath");

        // initializing our image view.
        imageView = findViewById(R.id.idIVImage);

        // on below line we are initializing our scale gesture detector for zoom in and out for our image.
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        // on below line we are getting our image file from its path.
        File imgFile = new File(imgPath);

        // if the file exists then we are loading that image in our image view.
        if (imgFile.exists()) {
            Picasso.get().load(imgFile).placeholder(R.drawable.ic_launcher_background).into(imageView);
        }
        return view;
    }

    public void setGalleryCallback(GalleryCallback galleryCallback) {
        this.galleryCallback = galleryCallback;
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
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        // inside on touch event method we are calling on
        // touch event method and passing our motion event to it.
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }
}