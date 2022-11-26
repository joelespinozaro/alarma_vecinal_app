package com.alarmavecinal.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GrupoContacto extends AppCompatActivity {
    Button agregarcontacto;
    Button listarcontacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_contacto);

        TextView textView1=(TextView) findViewById(R.id.textView1);
        textView1.setText(getIntent().getStringExtra("nombre_grupo"));
        TextView textView2=(TextView) findViewById(R.id.textView2);
        textView2.setText(getIntent().getStringExtra("descripcion"));

        agregarcontacto=(Button) findViewById(R.id.agregarcontactos);
        listarcontacto=(Button)findViewById(R.id.listarcontacto);

        agregarcontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(GrupoContacto.this, AgregarContacto.class);
                startActivity(intent);
            }
        });

        listarcontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(GrupoContacto.this, ListaContacto.class);
                startActivity(intent);
            }
        });

    }

}