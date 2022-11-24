package com.alarmavecinal.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.MutableLiveData;
public class RegisterActivity extends AppCompatActivity {
    EditText celular, nombre, apellido;
    Button registro;
    private User user;
    String token;
    private MutableLiveData<User> userExisted;
    private SharedPreferences preferences;

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
        /*Sacar Token*/
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }

                        // Get new FCM registration token
                        token = task.getResult();

                        // Log and toast
                        System.out.println("TOKEN " + token);
                    }
                });
        /***********************************/
        /*Inicializando el SharedPreferences*/
        preferences = getSharedPreferences("Preferences",MODE_PRIVATE);
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
            user.setToken(token);

            UserService.getInstance().saveUser(user);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("celular",celular);
            editor.putString("nombres",nombres);
            editor.putString("apellidos",apellidos);
            editor.putString("token",token);
            editor.commit();
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