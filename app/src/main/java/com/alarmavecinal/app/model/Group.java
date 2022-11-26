package com.alarmavecinal.app.model;
import com.google.firebase.firestore.DocumentId;

import java.util.List;


public class Group {
    public static final String COLLECTION_NAME = "groups";
    public static final String DOCUMENT_ID = "group_id";
    public static final String FIELD_NOMBRE = "celular";
    public static final String FIELD_DESCRIPTION = "nombres";
    public static final String FIELD_ADMINISTRADOR = "administrador";
    public static final String FIELD_INTEGRANTES = "apellidos";

    @DocumentId
    private String groupId;
    private String nombre;
    private String descripcion;
    private String administrador;
    private List<User> integrantes;

    public Group() {}

    public Group(String groupId, String nombre, String descripcion, String administrador, List<User> integrantes) {
        this.groupId = groupId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.administrador = administrador;
        this.integrantes = integrantes;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<User> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<User> integrantes) {
        this.integrantes = integrantes;
    }
}

