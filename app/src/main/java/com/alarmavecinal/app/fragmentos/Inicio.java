package com.alarmavecinal.app.fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alarmavecinal.app.MessageAlerta;
import com.alarmavecinal.app.R;


public class Inicio extends Fragment {

    ImageButton buttonAlerta;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_inicio, container, false);
        buttonAlerta = view.findViewById(R.id.btn_alert);

        buttonAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Enviando alerta",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(),MessageAlerta.class));
            }
        });
        return view;
    }



}