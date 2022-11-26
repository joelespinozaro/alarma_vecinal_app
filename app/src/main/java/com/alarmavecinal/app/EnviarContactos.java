package com.alarmavecinal.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EnviarContactos extends AppCompatActivity {

    TextView tvDatos;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lista_contactos);

        TextView textView=(TextView)findViewById(R.id.textView);

        Intent intent= getIntent();

        textView.setText(intent.getStringExtra("nombre"));
    }
}
