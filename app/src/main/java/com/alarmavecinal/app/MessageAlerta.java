package com.alarmavecinal.app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MessageAlerta extends AppCompatActivity {
    ImageButton btn_alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_alerta);

        ActionBar actionBar = getSupportActionBar();//Creamos un action Bar
        assert actionBar != null;
        actionBar.setTitle("Alerta");
        actionBar.setDisplayHomeAsUpEnabled(true);//Habilitamos el boton de retroceso
        actionBar.setDisplayShowHomeEnabled(true);
        btn_alert = findViewById(R.id.btn_alert);//Id del boton de alerta

        /*btn_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnviandoAlert();
            }
        }) ;*/

    }
    private void EnviandoAlert(){
        final ProgressDialog progDailog = ProgressDialog.show(
                MessageAlerta.this, "Testing", "Please wait...", true);
        new Thread() {
            public void run() {
                try {
                    // xml parser code here put...
                } catch (Exception e) {
                }
                progDailog.dismiss();
            }
        }.start();
    }
    /*Evento del Action Bar para volver a la actividad anterior*/
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}