package com.alarmavecinal.app.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alarmavecinal.app.R;
import com.google.android.material.textfield.TextInputLayout;

public class CrearGrupo extends Fragment {
    private EditText NombreGroupLayout,DescripcionGroupLayout,ContactGroupLayout;
    private String CodigoGroup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_crear_grupo, container, false);
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
        Log.d("CODIGO",this.CodigoGroup);
        Log.d("NOMBRE",nombreGrupo);
        Log.d("DESCRIPCION",descripcionGrupo);
        Log.d("CONTACTO",contactoGrupo);
    }

    public void generarCodigoGrupo(){
        this.CodigoGroup = "1234L";
    }
}