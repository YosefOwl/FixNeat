# Fix It Neat Android App
## About
This android app intended for Handyman to manage his jobs.
The app support 4 types of jobs : Window, Door, Pergola and Balcony.
In addition, the user can creat gallary of his jobs by using camera or upload from the device.

The Appliction written as apart of mobile application course at the college.

## Features
Authentication via FirebaseUI

Create new job order.
Display all order - recyclerview.
Track life cycle of job order.
Make call to client directly in app.

Save all order data in real time database, firebasa.

Create gallery of images.
Add image via camera phone.
Add image from gallery device.
Save images in firebase storage, the urls saved in real time under user.
 
## View example


https://user-images.githubusercontent.com/93843185/220553451-7acf803a-1446-4463-a654-f6fe6cff14e4.mp4






## Permissions
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

## Dependencies
    implementation 'com.google.firebase:firebase-database'
    implementation 'com.google.firebase:firebase-auth'

    implementation 'com.firebaseui:firebase-ui-auth:7.2.0'
    implementation 'com.google.firebase:firebase-storage:20.1.0'
    implementation 'com.squareup.picasso:picasso:2.5.2'
