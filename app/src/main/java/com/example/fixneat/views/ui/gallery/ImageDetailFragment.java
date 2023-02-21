package com.example.fixneat.views.ui.gallery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        Bundle args = getArguments();
        if (args != null) {
            imgPath = args.getString("imagePath");
            // use the data value as needed
        }
        View view = inflater.inflate(R.layout.fragment_image_detail, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        // initializing our image view.
        imageView = view.findViewById(R.id.idIVImage);

        // initializing scale gesture detector for zoom in and out for our image.
        scaleGestureDetector = new ScaleGestureDetector(this.getContext(), new ScaleListener());

        File imgFile = new File(imgPath);

        // if the file exists then we are loading that image in our image view.
        if (imgFile.exists()) {
            Picasso.with(getParentFragment().getContext()).load(imgFile).placeholder(R.drawable.ic_launcher_background).into(imageView);
        }
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
//        @Override
//        public boolean onTouchEvent(MotionEvent motionEvent) {
//            // inside on touch event method we are calling on
//            // touch event method and passing our motion event to it.
//            scaleGestureDetector.onTouchEvent(motionEvent);
//            return true;
//        }

    }

}