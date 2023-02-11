package com.example.fixneat.Viwes.ui.gallery;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class GalleryViewModel extends ViewModel {

    //private final MutableLiveData<String> mText;
    private final MutableLiveData<ArrayList<String>> mImagePaths;


    private ArrayList<String> imagePaths;



    private Context context;
    public GalleryViewModel setContext(Context context) {
        this.context = context;
        return this;
    }

    public GalleryViewModel() {
       // mText = new MutableLiveData<>();
        mImagePaths = new MutableLiveData<>();

        imagePaths = new ArrayList<>();
        loadImagePaths();
        mImagePaths.setValue(imagePaths);

        //mText.setValue("This is gallery fragment");
    }



//    public LiveData<String> getText() {
//        return mText;
//    }

    public LiveData<ArrayList<String>> getImagePaths() {
        return mImagePaths;
    }

    // in this method we are adding all our image paths
    private void loadImagePaths() {

        // checking if the device is having an sd card or not.
        boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

        if (isSDPresent) {

            // getting images data with their ids.
            final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};

            // order images by string.
            final String orderBy = MediaStore.Images.Media._ID;

            // store images in Cursor
            Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);

            // cursor.getCount() get total number of images
            for (int i = 0; i < cursor.getCount(); i++) {

                // move cursor position
                cursor.moveToPosition(i);

                // getting image file path
                int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

                imagePaths.add(cursor.getString(dataColumnIndex));
            }

            cursor.close();
        }
    }

}