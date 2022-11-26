package com.alarmavecinal.app.service;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.alarmavecinal.app.model.Group;
import com.alarmavecinal.app.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class GroupService {
    private static String TAG = "GroupService";
    private static FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore mFirestore;
    private FirebaseUser user;
    private User userFound;

    private static GroupService instance = null;

    private GroupService(){
        mFirestore = FirebaseFirestore.getInstance();
    }

    public static synchronized GroupService getInstance() {
        if (instance == null) {
            instance = new GroupService();
            mFirebaseAuth = FirebaseAuth.getInstance();
        }
        return instance;
    }

    public String saveGroup(Group group) {
        DocumentReference document;
//        List<User> users = new ArrayList<User>();
//        User user = addContact("","984590727");
//        users.add(user);

//        group.setIntegrantes(users);
        if (group.getGroupId() != null) {
            document = mFirestore.collection(Group.COLLECTION_NAME).document(group.getGroupId());
        } else {
            group.setGroupId(group.getGroupId());
            document = mFirestore.collection(Group.COLLECTION_NAME).document();
        }
        document.set(group);
        return document.getId();
    }
    //crear metodo para listar contactos del grupo;

//    public User addContact(String cod_grupo,String celular) {
//        User userFound;
////        try {
//            userFound = UserService.getInstance().getUserByCelular(celular);
//            Log.w("USUARIO ENCONTRADO", userFound.getCelular());
//            return userFound;
////        }catch (Exception e) {
////            Log.e("ERROR - USUARIO ECNONTRADO", e.toString());
////        }
//        //validar si existe el usuario por celular
//
//        //agregar al contacto al grupo
//    }
}
