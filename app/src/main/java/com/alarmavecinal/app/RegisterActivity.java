package com.alarmavecinal.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class RegisterActivity extends AppCompatActivity {
    EditText celular, nombre, apellido;
    Button registro;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registro = (Button) findViewById(R.id.register);
        celular = findViewById(R.id.celular);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            String name = account.getDisplayName();
            String lastName = account.getGivenName();

            nombre.setText(name);
            apellido.setText(lastName);
        }
    }
    public void Registrar(View view){
        Intent intent = new Intent(this,MainActivityUser.class);
        startActivity(intent);
        finish();
    }
}