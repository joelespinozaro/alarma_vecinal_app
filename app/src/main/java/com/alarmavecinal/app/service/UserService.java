package com.alarmavecinal.app.service;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.alarmavecinal.app.model.Group;
import com.alarmavecinal.app.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

public class UserService {
    private static String TAG = "UserService";
    private static FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore mFirestore;
    private FirebaseUser user;
    final List<User> users = new ArrayList<>();
//    private User userFound;
    private MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
    private static UserService instance = null;

    private UserService(){
        mFirestore = FirebaseFirestore.getInstance();
    }

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
            mFirebaseAuth = FirebaseAuth.getInstance();
        }
        return instance;
    }

    public MutableLiveData<User> getUserById(String userId) {
        MutableLiveData<User> liveProject = new MutableLiveData<>();

        final DocumentReference docRef = mFirestore.collection(User.COLLECTION_NAME).document(userId);
        docRef.addSnapshotListener((snapshot, e) -> {
            if (e != null) {
                Log.w(TAG, "Listen failed.", e);
                return;
            }

            if (snapshot != null && snapshot.exists()) {
                User user = snapshot.toObject(User.class);
                user.setUserId(snapshot.getId());
                liveProject.postValue(user);
            } else {
                Log.d(TAG, "Current data: null");
            }
        });

        return liveProject;
    }
    public String saveUser(User user) {
        DocumentReference document;

        if (user.getUserId() != null) {
            document = mFirestore.collection(User.COLLECTION_NAME).document(user.getUserId());
        } else {
            user.setUserId(user.getUserId());
            document = mFirestore.collection(User.COLLECTION_NAME).document();
        }

        document.set(user);
        return document.getId();
    }

//    public User getUserByCelular(String celular) {
//        mFirestore
//                .collection(User.COLLECTION_NAME)
//                .whereEqualTo("celular",celular)
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Log.d("92 - GET USER BY CELULAR", "obteniendo datos");
//                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
//                        for (DocumentSnapshot snapshot: snapshotList) {
//                            Log.d("95 - GET USER BY CELULAR", "datos conseguidos" + snapshot.getData().toString());
//                            users.add(snapshot.toObject(User.class));
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                       Log.e("105 - GET USER BY CELULAR", e.toString());
//                    }
//                });
//                Log.d("109 - GET USER BY CELULAR", "datos conseguidos" + users.get(0).toString());
//
//                return users.get(0);
//    }


    public String getCurrentUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Log.w("CURRENT USER!!!",user != null ? user.getUid() : "no existe el UID");
        return user != null ? user.getUid() : null;
    }
}
