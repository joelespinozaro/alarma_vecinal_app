package com.alarmavecinal.app.model;

import com.google.firebase.firestore.DocumentId;

public class User {
    public static final String COLLECTION_NAME = "users";
    public static final String DOCUMENT_ID = "user_id";
    public static final String FIELD_CELULAR = "celular";
    public static final String FIELD_NOMBRES = "nombres";
    public static final String FIELD_APELLIDOS = "apellidos";


    private String userId;
    private String celular;
    private String nombres;
    private String apellidos;

    public User() {}
    public User(String celular, String nombres, String apellidos) {
        this.userId = celular;
        this.celular = celular;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
