package com.alarmavecinal.app.service;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.alarmavecinal.app.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import javax.security.auth.callback.Callback;

public class UserService {
    private static String TAG = "UserService";
    private static FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore mFirestore;
    private FirebaseUser user;

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
        document = mFirestore.collection(User.COLLECTION_NAME).document(user.getUserId());
        document.set(user);
        return document.getId();
    }

    public String getCurrentUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Log.w("CURRENT USER!!!",user != null ? user.getUid() : "no existe el UID");
        return user != null ? user.getUid() : null;
    }
}
