package com.alarmavecinal.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alarmavecinal.app.model.User;
import com.alarmavecinal.app.service.UserService;

public class SesionActivity extends AppCompatActivity {
    private User user;
    Button sesion, registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);
        sesion = (Button) findViewById(R.id.InicioSesion);

    }

    public void goToMainActivity() {
        finish();
        Intent intent = new Intent(this, MainActivityUser.class);
        startActivity(intent);
    }

    public void Sesion(View view){
        Intent intent = new Intent(this, ValidarNumActivity.class);
        startActivity(intent);
        finish();
    }
}