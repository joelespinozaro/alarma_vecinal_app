package com.alarmavecinal.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alarmavecinal.app.model.User;
import com.alarmavecinal.app.service.UserService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.MutableLiveData;
public class RegisterActivity extends AppCompatActivity {
    EditText celular, nombre, apellido;
    Button registro;
    private User user;
    private MutableLiveData<User> userExisted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserService.getInstance().getUserById(UserService.getInstance().getCurrentUser())
                .observe(this, user -> {
                    this.user = user;
                    if (user != null) {
                        goToMainActivity();
                    }
                });

        registro = (Button) findViewById(R.id.register);
        celular = findViewById(R.id.celular);

        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);

        celular = findViewById(R.id.celular);
        celular.setText(getIntent().getStringExtra("userphone"));
    }

    public void onSave(View view) {
        EditText nombreView = (EditText) findViewById (R.id.nombre);
        EditText apellidoView = (EditText) findViewById (R.id.apellido);
        EditText celularView = (EditText) findViewById(R.id.celular);

        String celular = celularView.getText().toString();
        String nombres = nombreView.getText().toString();
        String apellidos = apellidoView.getText().toString();

        if (!celular.isEmpty() && !nombres.isEmpty() && !apellidos.isEmpty()) {
            if (user == null) {
                user = new User();
                user.setUserId(UserService.getInstance().getCurrentUser());
            }
            user.setCelular(celular);
            user.setNombres(nombres);
            user.setApellidos(apellidos);

            UserService.getInstance().saveUser(user);
            goToMainActivity();
        } else {
            Log.w("ONSAVE USER", "ERROR");
        }

        super.onPause();
    }

    public void goToMainActivity() {
        finish();
        Intent intent = new Intent(this, MainActivityUser.class);
        startActivity(intent);
    }
}