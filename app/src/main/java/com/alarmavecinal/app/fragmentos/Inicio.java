package com.alarmavecinal.app.fragmentos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alarmavecinal.app.MessageAlerta;
import com.alarmavecinal.app.R;
import com.alarmavecinal.app.model.User;
import com.alarmavecinal.app.service.FCMSend;


public class Inicio extends Fragment {
    EditText _message;
    ImageButton buttonAlerta;
    private Context _context;
    User user;
    String toke="ct9h9litRf-O3jexq-2Ch7:APA91bFQ9hUJC480X8a8SLH-OHGMdaBigXjATRXkneRlc-xPFGHGXWyHPH1UMmzZtws6ROKEfxQnDRZL8a1AwFDj9d8CiMH669ncgFln7RIayaVWmsf4SEklaRi4mcmJz9HxRkuhimR3";
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_inicio, container, false);
        buttonAlerta = view.findViewById(R.id.btn_alert);
        _message = view.findViewById(R.id.messagefcm);
        buttonAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "Alerta Vecinal";
                String message = _message.getText().toString().trim();
                if(!title.equals("") && !message.equals("")){
                    FCMSend.pushNotification(_context, toke, title,message);
                }
                Toast.makeText(_context, "Enviando Alerta", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),MessageAlerta.class);
                startActivity(intent);
            }
        });
        return view;
    }



}