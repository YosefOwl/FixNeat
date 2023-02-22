package com.example.fixneat.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.fixneat.R;


public class ImageLoader {

    private static ImageLoader instance;
    private static Context appContext;


    private ImageLoader(Context context) {
        appContext = context;
    }


    public static ImageLoader getInstance() {
        return instance;
    }

    public static void initInstance(Context context) {
        if (instance == null)
            instance = new ImageLoader(context);
    }


    public void load (String path, ImageView imageView){
        Glide.with(appContext).load(path).centerCrop()
                .placeholder(R.drawable.ic_launcher_background).into(imageView);
    }

}
