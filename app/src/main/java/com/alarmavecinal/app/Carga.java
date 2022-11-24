package com.alarmavecinal.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.alarmavecinal.app.fragmentos.Inicio;

public class Carga extends AppCompatActivity {
    TextView app_name;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carga);
        /*Cambio de letra*/
        String ubicacion = "fuentes/milky_honey.ttf";
        Typeface tf = Typeface.createFromAsset(Carga.this.getAssets(),ubicacion);
        /*----------*/
        app_name = (TextView) findViewById(R.id.app_name);
        preferences = getSharedPreferences("Preferences",MODE_PRIVATE);
        final int DURACION = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Codigo que se ejecutara
                String celular = preferences.getString("celular",null);
                String nombre = preferences.getString("nombres",null);
                String apellido = preferences.getString("apellidos",null);
                String token = preferences.getString("token",null);
                if(celular!= null && nombre != null && apellido != null && token != null){
                    Intent intent = new Intent(Carga.this, MainActivityUser.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(Carga.this, SesionActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },DURACION);
        //Envio de nuevo formato
        app_name.setTypeface(tf);
    }



}