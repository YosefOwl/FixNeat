package com.example.fixneat.Repository;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;

public class ImageRepo {

    private static ImageRepo instance;

    static final String DELIMITER  = "/";
    static final String ABS_ROOT = "USERS";
    static final String ORDERS = "ORDERS";
    static final String ORDERS_IMAGES = "ORDERS_IMAGES";
    static final String GALLERY_IMAGES = "GALLERY_IMAGES/";

    private ArrayList<File> images = new ArrayList<>();
    private MutableLiveData<ArrayList<File>> mldImagesList = new MutableLiveData<>();

    private String userId;


    private final StorageReference storageRef;

    private ImageRepo() {
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        storageRef = FirebaseStorage.getInstance().getReference();
    }

    public static synchronized ImageRepo getInstance() {
        if (instance == null)
            instance = new ImageRepo();
        return instance;
    }

    public void  uploadImageToFirebase(String fileName, Uri contentUri, String imageTag) {

        StorageReference image = storageRef.child(GALLERY_IMAGES).child(imageTag).child(fileName);

        image.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        OrderRepo.getInstance().insertGalleryImgUrl(imageTag, uri.toString(), false);
                        Log.d("ImageRepo", "onSuccess: Uploaded Image URl is " + uri);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("ImageRepo", "onFailure: Upload Failed, URl is " + contentUri);
            }
        });
    }

    public void retrieveImageUriFromFirebase() {
    }
    public MutableLiveData<ArrayList<File>> getGalleryImages () {
        if (images.size() == 0) // this for reload == -1
            loadImages();

        mldImagesList.setValue(images);
        return mldImagesList;
    }

    private void loadImages() {

//        StorageReference galleryImage = storageRef.child(galleryImgPath);
    }

    public void deleteImage() {
        // not implement yet
    }

    public void insertImage(Uri imageUri, String imageTag) {
//        StorageReference imagePath = storageRef.child(galleryImgPath);
    }

    public void insertImage(Uri imageUri, String imageTag, String orderId) {
//        StorageReference imagePath = storageRef.child(galleryImgPath);
    }


}
