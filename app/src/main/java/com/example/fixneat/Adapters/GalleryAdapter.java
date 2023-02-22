package com.example.fixneat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fixneat.Interfaces.GalleryCallback;
import com.example.fixneat.R;
import com.example.fixneat.Utils.ImageLoader;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private final Context context;
    private final ArrayList<String> imagePathsList;

    private GalleryCallback galleryCallback;

    public GalleryAdapter(Context context) {
        this.context = context;
        this.imagePathsList = new ArrayList<>();
    }

    public void setImagePathsList(ArrayList<String> imagePaths) {
        if (imagePathsList != null) {
            this.imagePathsList.clear();
            this.imagePathsList.addAll(imagePaths);
            notifyDataSetChanged();
        }
    }

    public void setGalleryCallback(GalleryCallback galleryCallback) {
        this.galleryCallback = galleryCallback;
    }

    private String getItem(int position) {
        return imagePathsList.get(position);
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_item, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {

        String imagePath = getItem(position);
        ImageLoader.getInstance().load(imagePath, holder.imageIV);
    }

    @Override
    public int getItemCount() {
        return imagePathsList == null ? 0 : imagePathsList.size();
    }

    // View Holder Class to handle Recycler View.
    public class GalleryViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageIV;

        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageIV = itemView.findViewById(R.id.idIVImage);

            itemView.setOnClickListener(view -> {
                galleryCallback.displayImage(getItem(getAdapterPosition()), getAdapterPosition());
            });
        }
    }
}
