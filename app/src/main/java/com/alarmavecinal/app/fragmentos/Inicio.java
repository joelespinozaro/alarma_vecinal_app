package com.alarmavecinal.app.fragmentos;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Inicio extends Fragment {
    EditText _message;
    ImageButton buttonAlerta;
    Button policia, bombero;
    private Context _context;
    User user;
    // Definimos una ArrayList
    List<String> tokens = new ArrayList<String>();
    // Añadimos elementos

//    String toke="dROMytTlTEWz1jbGgS1Oq-:APA91bHtLETn47Nr-Mv4MY5cfHOKUkKxnc3SRlM8nZurvhAbVPrXTRiXQeS_4mQwUzoiAbwT8HO35AzIKsuceCl56y6pBq2N_gpUVGzOOfuiZvFSTCbkXOl9j9VwSJV8iZP8F2LXlpev";
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

                tokens.add("dROMytTlTEWz1jbGgS1Oq-:APA91bHtLETn47Nr-Mv4MY5cfHOKUkKxnc3SRlM8nZurvhAbVPrXTRiXQeS_4mQwUzoiAbwT8HO35AzIKsuceCl56y6pBq2N_gpUVGzOOfuiZvFSTCbkXOl9j9VwSJV8iZP8F2LXlpev");
                tokens.add("fsPAa8hSSvSc1ebeHSAYNe:APA91bFc7UhzcK7wGJYH9qNjs8PiHzzA3yyGwzPuX3SXkVw4ObRJpVgZspSEkXzI7c-QtX_PPThEMe8PFKeldvIencb1SwFGdQAJfoaCJb3NR9Qea7jahHY9J3ol4G2v1rBdO3Yd2TFJ");

                for (int i = 0; i < tokens.size(); i++) {
                    try {
                        String token = tokens.get(i);
                        FCMSend.pushNotification(_context, token, title,message);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Toast.makeText(_context, "Enviando Alerta", Toast.LENGTH_SHORT).show();
            }
        });

        bombero=(Button) view.findViewById(R.id.bombero);
        policia=(Button) view.findViewById(R.id.policia);


        bombero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent p=new Intent(Intent.ACTION_CALL, Uri.parse("tel:116"));
                if(ActivityCompat.checkSelfPermission(Inicio.this._context, Manifest.permission.CALL_PHONE)!=
                        PackageManager.PERMISSION_GRANTED)
                    return;
                startActivity(p);*/
                int permiso = ContextCompat.checkSelfPermission(_context,Manifest.permission.CALL_PHONE);
                if(permiso != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(_context, "No tiene permisos de llamada", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},255);
                }else{
                    String numero = "116";
                    String incio = "tel:"+numero;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(incio));
                    startActivity(intent);
                }
            }
        });

        policia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent p=new Intent(Intent.ACTION_CALL, Uri.parse("tel:105"));
                if(ActivityCompat.checkSelfPermission(Inicio.this._context, Manifest.permission.CALL_PHONE)!=
                        PackageManager.PERMISSION_GRANTED)
                    return;
                startActivity(p);*/
                int permiso = ContextCompat.checkSelfPermission(_context,Manifest.permission.CALL_PHONE);
                if(permiso != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(_context, "No tiene permisos de llamada", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},255);
                }else{
                    String numero = "105";
                    String incio = "tel:"+numero;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(incio));
                    startActivity(intent);
                }
            }
        });



        bombero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p=new Intent(Intent.ACTION_CALL, Uri.parse("tel:116"));
                if(ActivityCompat.checkSelfPermission(Inicio.this._context, Manifest.permission.CALL_PHONE)!=
                        PackageManager.PERMISSION_GRANTED)
                    return;
                startActivity(p);
            }
        });

        policia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p=new Intent(Intent.ACTION_CALL, Uri.parse("tel:105"));
                if(ActivityCompat.checkSelfPermission(Inicio.this._context, Manifest.permission.CALL_PHONE)!=
                        PackageManager.PERMISSION_GRANTED)
                    return;
                startActivity(p);
            }
        });






        return view;
    }



}