package com.alarmavecinal.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {
    Button registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registro = (Button) findViewById(R.id.register);
    }
    public void Registrar(View view){
        Intent intent = new Intent(this,MainActivityUser.class);
        startActivity(intent);
        finish();
    }
}