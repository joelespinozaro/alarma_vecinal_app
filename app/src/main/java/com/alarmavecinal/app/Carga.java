package com.alarmavecinal.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class Carga extends AppCompatActivity {
    TextView app_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carga);
        /*Cambio de letra*/
        String ubicacion = "fuentes/milky_honey.ttf";
        Typeface tf = Typeface.createFromAsset(Carga.this.getAssets(),ubicacion);
        /*----------*/
        app_name = (TextView) findViewById(R.id.app_name);
        final int DURACION = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Codigo que se ejecutara
                Intent intent = new Intent(Carga.this, SesionActivity.class);
                startActivity(intent);
                finish();
            }
        },DURACION);
        //Envio de nuevo formato
        app_name.setTypeface(tf);
    }


}