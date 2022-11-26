package com.alarmavecinal.app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AgregarContacto extends AppCompatActivity {

    EditText etMsj, etCel;
    FloatingActionButton btnSelec;
    Button btnguardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);

        etMsj = findViewById(R.id.editTextTextPersonName);
        etCel = findViewById(R.id.editTextTextPersonNumber);


        btnSelec = findViewById(R.id.btnSelec);

        Button button= (Button)findViewById(R.id.button);
        EditText editTextTextPersonName= (EditText)findViewById(R.id.editTextTextPersonName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(AgregarContacto.this, EnviarContactos.class);
                intent.putExtra("nombre", editTextTextPersonName.getText().toString());
                startActivity(intent);


            }
        });




        if (ActivityCompat.checkSelfPermission(AgregarContacto.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AgregarContacto.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }


        btnSelec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

                startActivityForResult(intent, 1);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){

            Uri uri = data.getData();
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()){

                int indiceName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int indiceNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                String nombre = cursor.getString(indiceName);
                String numero = cursor.getString(indiceNumber);

                numero = numero.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");

                etMsj.setText(nombre);
                etCel.setText(numero);

            }

        }

    }

}