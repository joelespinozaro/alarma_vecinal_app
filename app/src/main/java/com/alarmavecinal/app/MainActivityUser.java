package com.alarmavecinal.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.alarmavecinal.app.fragmentos.CrearGrupo;
import com.alarmavecinal.app.fragmentos.Inicio;
import com.alarmavecinal.app.fragmentos.Perfil;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivityUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        Toolbar toolbar = findViewById(R.id.toolbarU);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout_U);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open
        ,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Fragmento por defecto
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Inicio()).commit();
            navigationView.setCheckedItem(R.id.InicioUser);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.InicioUser :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Inicio()).commit();
                break;
            case R.id.PerfilUser:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Perfil()).commit();
                break;
            case R.id.RegistrarGrupo:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CrearGrupo()).commit();
                break;
            case R.id.Salir:
                Toast.makeText(this, "Cerraste Sesion", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}