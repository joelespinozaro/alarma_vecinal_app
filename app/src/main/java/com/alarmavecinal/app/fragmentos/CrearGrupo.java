package com.alarmavecinal.app.fragmentos;

import android.content.Context;
import android.content.Intent;
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

import com.alarmavecinal.app.GrupoContacto;
import com.alarmavecinal.app.R;
import com.alarmavecinal.app.model.Group;
import com.alarmavecinal.app.model.User;
import com.alarmavecinal.app.service.GroupService;
import com.alarmavecinal.app.service.UserService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CrearGrupo extends Fragment {
    private EditText NombreGroupLayout,DescripcionGroupLayout,ContactGroupLayout;
    private Integer CodigoGroup;
    private FirebaseFirestore mFireStore;
    private Context _context;
    private Group group;

    public void onAttach(Context context) {
        super.onAttach(context);
        _context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crear_grupo, container, false);
        mFireStore = FirebaseFirestore.getInstance();
        Button btnNewGroup = (Button) view.findViewById(R.id.guardarGrupo);
        NombreGroupLayout = (EditText) view.findViewById(R.id.nameGroup);
        DescripcionGroupLayout = (EditText) view.findViewById(R.id.descripcion);
        //ContactGroupLayout = (EditText) view.findViewById(R.id.contact);

        btnNewGroup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setRandomCodeGroup();
                newGroup();
                Intent intent= new Intent(_context, GrupoContacto.class);
                intent.putExtra("nombre_grupo", NombreGroupLayout.getText().toString());
                intent.putExtra("descripcion", DescripcionGroupLayout.getText().toString());
                startActivity(intent);
            }
        });
        return view;
    }

    public void newGroup(){
        String nombreGrupo =  this.NombreGroupLayout.getText().toString();
        String descripcionGrupo =  this.DescripcionGroupLayout.getText().toString();
        //String contactoGrupo =  this.ContactGroupLayout.getText().toString();

        if(!nombreGrupo.matches("") && !descripcionGrupo.matches("")){
            List<User> users = new ArrayList<User>();

            if (group == null) {
                group = new Group();
            }
            group.setNombre(nombreGrupo);
            group.setDescripcion(descripcionGrupo);
            group.setAdministrador(UserService.getInstance().getCurrentUser());
            GroupService.getInstance().saveGroup(group);

//            addContact(String cod_grupo,String celular)
//            Toast.makeText(getActivity(),"Grupo Creado Exitosamente",Toast.LENGTH_LONG ).show();
//            Toast.makeText(getActivity(),"Error al crear grupo",Toast.LENGTH_LONG ).show();
        }else{
            Toast.makeText(getActivity(),"Complete los campos",Toast.LENGTH_LONG ).show();
        }



    }
    public void setRandomCodeGroup(){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        this.CodigoGroup = random.nextInt(10000);
    }

}