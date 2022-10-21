package com.alarmavecinal.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SesionActivity extends AppCompatActivity {
    Button sesion, registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);
        sesion = (Button) findViewById(R.id.InicioSesion);
        registro = (Button) findViewById(R.id.Registrar);
    }
    public void Sesion(View view){
        Intent intent = new Intent(this, MainActivityUser.class);
        startActivity(intent);
        finish();
    }
    public void Registrar(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}