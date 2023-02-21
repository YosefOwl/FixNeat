package com.example.fixneat.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.fixneat.Model.Order;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderRepo {

    static final String ABS_ROOT = "USERS";
    static final String ORDERS = "ORDERS";
    static final String GALLERY_IMAGES_URL = "GALLERY_IMAGES_URL";
    static final String ORDERS_IMAGES_URL = "ORDERS_IMAGES_URL";

    private static OrderRepo instance;

    private DataCallback callback;

    private MutableLiveData<ArrayList<Order>> mldOrdersList = new MutableLiveData<>();
    private final String userId;
    private DatabaseReference rootRef;

    private OrderRepo() {
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        rootRef = FirebaseDatabase.getInstance().getReference();

    }

    public static synchronized OrderRepo getInstance() {
        if (instance == null) {
            instance = new OrderRepo();
        }
        return instance;
    }

    public void setCallback(DataCallback callback) {
        this.callback = callback;
    }

    public void getOrders() {

        Query query = rootRef.child(ABS_ROOT).child(userId).child(ORDERS);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Order> orders = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        orders.add(snapshot.getValue((Order.class)));
                    }
                    callback.uploadFinish(orders);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("OrderRepo ", error.toString());
            }
        });
    }

    public void insertOrder(Order order) {

        DatabaseReference dbRef = rootRef.child(ABS_ROOT).child(userId).child(ORDERS);

        String orderKey = dbRef.push().getKey();
        order.setId(orderKey);

        dbRef.child(orderKey).setValue(order).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("OrderRepo ", "success INSERT\n" + order);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("OrderRepo ", "insert failed\n" + order);
            }
        });
    }

    public void updateOrder(Order order, boolean isUpdateOnlyStatus) {
        String status = "currentStatus";
        HashMap <String, Object> toUpdate = new HashMap<>();
        toUpdate.put(status, order.getCurrentStatus());

        if (!isUpdateOnlyStatus) {
            String jobs = "jobs";
            String date = "installVisitD_T";
            String note = "note";

            toUpdate.put(jobs, order.getJobs());
            toUpdate.put(date, order.getInstallVisitD_T());
            toUpdate.put(note, order.getNote());
        }

        DatabaseReference dbRef = rootRef.child(ABS_ROOT).child(userId).child(ORDERS);

        dbRef.child(order.getId()).updateChildren(toUpdate).addOnSuccessListener(new OnSuccessListener<Void>() {
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
    public void insertGalleryImgUrl(String tag, String imageUrl, boolean isOrderImage) {

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child(ABS_ROOT).child(userId);

        if (isOrderImage)
            dbRef = dbRef.child(ORDERS_IMAGES_URL);
        else
            dbRef = dbRef.child(GALLERY_IMAGES_URL);

        dbRef.child(tag).setValue(imageUrl).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("RT_DB :", "insert url success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("RT_DB :", "insert url failed :" + e);
            }
        });
    }


    public void deleteOrder() {
//        dbRef.child(rootPath).setValue(null);
//        dbRef.child(rootPath).removeValue(); // same as set with null
    }
}
