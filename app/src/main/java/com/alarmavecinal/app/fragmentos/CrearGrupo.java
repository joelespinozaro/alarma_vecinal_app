package com.alarmavecinal.app.fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alarmavecinal.app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearGrupo extends Fragment {
    private EditText NombreGroupLayout,DescripcionGroupLayout,ContactGroupLayout;
    private String CodigoGroup;
    private FirebaseFirestore mFireStore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_crear_grupo, container, false);
        mFireStore = FirebaseFirestore.getInstance();
        Button btnNewGroup = (Button) view.findViewById(R.id.guardarGrupo);
        NombreGroupLayout = (EditText) view.findViewById(R.id.nameGroup);
        DescripcionGroupLayout = (EditText) view.findViewById(R.id.descripcion);
        ContactGroupLayout = (EditText) view.findViewById(R.id.contact);

        btnNewGroup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                generarCodigoGrupo();
                newGroup();
            }
        });
        return view;
    }

    public void newGroup(){
        String nombreGrupo =  this.NombreGroupLayout.getText().toString();
        String descripcionGrupo =  this.DescripcionGroupLayout.getText().toString();
        String contactoGrupo =  this.ContactGroupLayout.getText().toString();

        Map<String, Object> map = new HashMap<>();
        map.put("codgru",this.CodigoGroup);
        map.put("nombre",nombreGrupo);
        map.put("descripcion",descripcionGrupo);

        this.mFireStore.collection("grupos").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getActivity(),"Grupo Creado Exitosamente",Toast.LENGTH_LONG ).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"Error al crear grupo",Toast.LENGTH_LONG ).show();
            }
        });

    }

    public void generarCodigoGrupo(){
        this.CodigoGroup = "1234L";
    }
}