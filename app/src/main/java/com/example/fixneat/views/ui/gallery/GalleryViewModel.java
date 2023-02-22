package com.example.fixneat.views.ui.gallery;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fixneat.Repository.ImageDataCallback;
import com.example.fixneat.Repository.ImageRepo;

import java.util.ArrayList;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<String>> mImagePaths;
    private ImageRepo imageRepository;

    ImageDataCallback imageDataCallback = new ImageDataCallback() {
        @Override
        public void uploadFinish(ArrayList<String> imagesList) {
            mImagePaths.setValue(imagesList);
        }
    };

    public GalleryViewModel() {

        mImagePaths = new MutableLiveData<>();
        imageRepository = ImageRepo.getInstance();
        imageRepository.setCallback(imageDataCallback);
        imageRepository.getGalleryImagesUrl();
    }


    public void insert(String fileName, Uri contentUri, String imageTag) {
        imageRepository.insertImage(fileName, contentUri, imageTag);
    }

    public LiveData<ArrayList<String>> getImages() {
        return mImagePaths;
    }

    public void update(Uri uri, String fileName) {

    }

    public void delete(String imagePath) {
        imageRepository.deleteImage();
    }

}