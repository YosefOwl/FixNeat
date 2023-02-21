package com.example.fixneat.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixneat.Interfaces.GalleryCallback;
import com.example.fixneat.R;
import com.example.fixneat.views.ui.gallery.ImageDetailFragment;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private final Context context;
    private final ArrayList<String> imagePaths;

    GalleryCallback galleryCallback;

    public GalleryAdapter(Context context, ArrayList<String> imagePaths) {
        this.context = context;
        this.imagePaths = imagePaths;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_item, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {


        File imgFile = new File(imagePaths.get(position));

        if (imgFile.exists()) {

            Picasso.with(context).load(imgFile).placeholder(R.drawable.ic_launcher_background).into(holder.imageIV);
             //if the file exists then we are displaying that file in our image view using picasso library.
//            Picasso.get().load(imgFile).placeholder(R.drawable.ic_launcher_background).into(holder.imageIV);
//
//             //on below line we are adding click listener to our item of recycler view.
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                     //inside on click listener we are creating a new intent
//                    Intent i = new Intent(context, ImageDetailFragment.class);
//
//                    // on below line we are passing the image path to our new activity.
//                    i.putExtra("imgPath", imagePathArrayList.get(position));
//                    // at last we are starting our activity.
//                    context.startActivity(i);
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return imagePaths == null ? 0 : imagePaths.size();
    }

    private String getItem(int position) {
        return imagePaths.get(position);
    }

    public void setGalleryCallback(GalleryCallback galleryCallback) {
        this.galleryCallback = galleryCallback;
    }


    // View Holder Class to handle Recycler View.
    public class GalleryViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageIV;

        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.
            imageIV = itemView.findViewById(R.id.idIVImage);

            itemView.setOnClickListener(view -> {
                galleryCallback.displayImage(getItem(getAdapterPosition()), getAdapterPosition());
            });

        }
    }
}
