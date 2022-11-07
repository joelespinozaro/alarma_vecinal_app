package com.alarmavecinal.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText celular, nombre, apellido;
    Button registro;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("sampleData/usuario");
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

    public void onSave(View view) {
        EditText nombreView = (EditText) findViewById (R.id.nombre);
        EditText apellidoView = (EditText) findViewById (R.id.apellido);
        String name = nombreView.getText ().toString();
        String lastName = apellidoView.getText ().toString();
        Map<String, Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put("name", name); dataToSave.put("lastname", lastName);
        mDocRef.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("TAG", "usuario registrado correctamente.");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("TAG", "usuario registrado correctamente.");
            }
        });
    }
    public void Registrar(View view){
        Intent intent = new Intent(this,MainActivityUser.class);
        startActivity(intent);
        finish();
    }
}