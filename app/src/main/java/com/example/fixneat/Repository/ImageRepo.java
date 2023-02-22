package com.example.fixneat.Repository;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;

public class ImageRepo {

    private static ImageRepo instance;

    static final String ABS_ROOT = "USERS";
    static final String ORDERS_IMAGES = "ORDERS_IMAGES";
    static final String GALLERY_IMAGES = "GALLERY_IMAGES";
    static final String GALLERY_IMAGES_URL = "GALLERY_IMAGES_URL";
    static final String ORDERS_IMAGES_URL = "ORDERS_IMAGES_URL";

    private String userId;

    private final StorageReference storageRef;
    private final DatabaseReference rootRtDbRef;

    private ImageDataCallback imageDataCallback;

    private ImageRepo() {
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        storageRef = FirebaseStorage.getInstance().getReference();
        rootRtDbRef = FirebaseDatabase.getInstance().getReference();
    }

    public static synchronized ImageRepo getInstance() {
        if (instance == null)
            instance = new ImageRepo();
        return instance;
    }

    public void setCallback(ImageDataCallback imageDataCallback) {
        this.imageDataCallback = imageDataCallback;
    }

    public void getGalleryImagesUrl() {

        Query query = rootRtDbRef.child(ABS_ROOT).child(userId).child(GALLERY_IMAGES_URL).child("Pergola");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> imagesUrlFromRT = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot dss : snapshot.getChildren()) {
                        imagesUrlFromRT.add(dss.getValue(String.class));
                    }
                    imageDataCallback.uploadFinish(imagesUrlFromRT);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("ImageRepo ", "onCancelled: fail to load url images " + error);
            }
        });
    }


    private void insertGalleryImgUrl(String tag, String imageUrl, boolean isOrderImage) {

        DatabaseReference dbRef;
        if (isOrderImage)
            dbRef = rootRtDbRef.child(ABS_ROOT).child(userId).child(ORDERS_IMAGES_URL).child(tag);
        else
            dbRef = rootRtDbRef.child(ABS_ROOT).child(userId).child(GALLERY_IMAGES_URL).child(tag);

        String key = dbRef.push().getKey();
        dbRef.child(key).setValue(imageUrl).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    // boolean isOrderImage, true is order image otherwise gallery
    public void insertImage(String fileName, Uri contentUri, String imageTag) {

        StorageReference image = storageRef.child(GALLERY_IMAGES).child(imageTag).child(fileName);

        image.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        insertGalleryImgUrl(imageTag, uri.toString(), false);
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

    public void deleteImage() {
        // not implement yet
    }

}
