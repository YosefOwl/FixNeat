package com.example.fixneat.views.ui.gallery;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.fixneat.Adapters.GalleryAdapter;
import com.example.fixneat.BuildConfig;
import com.example.fixneat.Interfaces.GalleryCallback;
import com.example.fixneat.R;
import com.example.fixneat.Utils.SignalUser;
import com.example.fixneat.databinding.FragmentGalleryBinding;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GalleryFragment extends Fragment {

    public static final int CAMERA_PER_REQ_CODE = 200;
    public static final int CAMERA_REQUEST_CODE = 300;
    public static final int GALLERY_REQUEST_CODE = 400;

    private FragmentGalleryBinding binding;
    private GalleryAdapter galleryAdapter;
    private GalleryViewModel galleryViewModel;


    private String currentPhotoPath;
    private ProgressDialog progressDialog;

    GalleryCallback galleryCallback = (imagePath, position) -> {
        showImage(imagePath);
    };

    public GalleryFragment() {}

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGalleryBinding.inflate(inflater, container, false);

        binding.galleryRCV.setHasFixedSize(true);
        binding.galleryRCV.setLayoutManager(new GridLayoutManager(this.getContext(), 4));

        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        galleryViewModel.getImages().observe(getViewLifecycleOwner(), observer);

        galleryAdapter = new GalleryAdapter(getContext());
        galleryAdapter.setGalleryCallback(galleryCallback);

        binding.galleryRCV.setAdapter(galleryAdapter);

        initButtons();

        return binding.getRoot();
    }


    private void showImage(String imagePath) {

        ImageDetailFragment fragment = new ImageDetailFragment();
        Bundle bundle = new Bundle();

        bundle.putString(ImageDetailFragment.IMAGE_PATH, imagePath);
        fragment.setArguments(bundle);

        replaceFragment(fragment);
    }

    private void initButtons() {

        binding.galleryFABCamera.setOnClickListener(v-> {

            askCameraPermissions();
        });

        binding.galleryFABGalleryLoad.setOnClickListener(view -> {

            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallery, GALLERY_REQUEST_CODE);
        });
    }

    private void askCameraPermissions() {

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(requireActivity(), new String[] {Manifest.permission.CAMERA}, CAMERA_PER_REQ_CODE);
        else
            dispatchTakePictureIntent();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == CAMERA_PER_REQ_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                dispatchTakePictureIntent();
            else
                SignalUser.getInstance().toast("Camera Permission is Required to Use camera.");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (Activity.RESULT_OK == resultCode){
            if (requestCode == CAMERA_REQUEST_CODE) {

                File filePath = new File(currentPhotoPath);

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(filePath);
                mediaScanIntent.setData(contentUri);
                this.getContext().sendBroadcast(mediaScanIntent);
                openPhotoDialog(contentUri, filePath.getName());
            }
            if (requestCode == GALLERY_REQUEST_CODE) {

                Uri contentUri = data.getData();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "" + timeStamp + "." + getFileExt(contentUri);
                Log.d("onActivityResult", " Gallery Image Uri: " +  imageFileName);
                openPhotoDialog(contentUri, imageFileName);
            }
        }
    }

    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(requireContext().getPackageManager()) != null) {

            File photoFile = createImageFile();
            currentPhotoPath = photoFile.getAbsolutePath();

            if (photoFile != null) {
                Uri uriForFile = FileProvider.getUriForFile(requireContext(), BuildConfig.APPLICATION_ID + ".provider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = this.getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }

    private File createImageFile()  {

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "" + timeStamp + "_";

        File image=null;
        try {
            image = File.createTempFile(imageFileName,  ".jpg", requireContext().getFilesDir());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    private void replaceFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_content_menu, fragment).addToBackStack(null)
                .commit();
    }

    Observer<ArrayList<String>> observer = new Observer<ArrayList<String>>() {
        @Override
        public void onChanged(ArrayList<String> images) {

            if (images == null) {
                binding.noGalleryMsgTV.setVisibility(View.VISIBLE);
            }
            else if (images.size() == 0){
                binding.noGalleryMsgTV.setVisibility(View.VISIBLE);
            }

            else {
                binding.noGalleryMsgTV.setVisibility(View.GONE);
                binding.galleryRCV.setVisibility(View.VISIBLE);

                galleryAdapter.setGalleryCallback(galleryCallback);
                galleryAdapter.setImagePathsList(images);
                binding.galleryRCV.setAdapter(galleryAdapter);
                galleryAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void openPhotoDialog(Uri imageUri, String imageName) {

        Dialog dialog = new Dialog(this.getContext());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = getLayoutInflater().inflate(R.layout.dialog_upload_photo, null);
        view.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        ImageView imageView = view.findViewById(R.id.photo_dialog_IMGV);
        Button saveBTN = view.findViewById(R.id.save_BTN);
        Button cancelBTN = view.findViewById(R.id.cancel_BTN);
        Spinner spinner = view.findViewById(R.id.photo_tags_SP);

        imageView.setImageURI(imageUri);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String tag = parent.getItemAtPosition(pos).toString();

                String finalTag = tag;
                saveBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (finalTag == null) {
                            SignalUser.getInstance().toast("Upload Photo Required Tag Name");
                        }
                        else {
                            galleryViewModel.insert(imageName, imageUri, finalTag);
                            dialog.dismiss();
                            SignalUser.getInstance().toast("Photo Uploaded");
                        }
                    }
                });
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        imageView.requestLayout();
        dialog.setContentView(view);
        dialog.show();
    }

}