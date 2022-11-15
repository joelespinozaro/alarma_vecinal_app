package com.alarmavecinal.app.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alarmavecinal.app.R;
import com.alarmavecinal.app.model.User;
import com.alarmavecinal.app.service.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Perfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Perfil extends Fragment {
    EditText celular, nombre, apellido;
    Button btnSave;
    private FirebaseUser currentUser;
    private User user;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Perfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Perfil.
     */
    // TODO: Rename and change types and number of parameters
    public static Perfil newInstance(String param1, String param2) {
        Perfil fragment = new Perfil();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        EditText nombreView = (EditText) view.findViewById(R.id.nombre);
        EditText apellidoView = (EditText) view.findViewById (R.id.apellido);
        EditText celularView = (EditText) view.findViewById(R.id.celular);
        Button btnSaveView = (Button) view.findViewById(R.id.btnSave);

        UserService.getInstance().getUserById(UserService.getInstance().getCurrentUser())
                .observe(getViewLifecycleOwner(), user -> {
                    this.user = user;
                    if (user != null) {
                        celularView.setText(user.getCelular());
                        nombreView.setText(user.getNombres());
                        apellidoView.setText(user.getApellidos());
                    }
                });

        btnSaveView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                onClick2(v);
                EditText nombreView = (EditText) view.findViewById(R.id.nombre);
                EditText apellidoView = (EditText) view.findViewById (R.id.apellido);
                EditText celularView = (EditText) view.findViewById(R.id.celular);

                Log.e("NOMBRE VIEW", nombreView != null ? nombreView.getText().toString() : "ESTÁ VACIO EL NOMBREVIEW");

                String celular = celularView.getText().toString();
                String nombres = nombreView.getText().toString();
                String apellidos = apellidoView.getText().toString();

                try {
                    if (!celular.isEmpty() && !nombres.isEmpty() && !apellidos.isEmpty()) {
                        if (user == null) {
                            user = new User();
                            user.setUserId(UserService.getInstance().getCurrentUser());
                        }
                        user.setCelular(celular);
                        user.setNombres(nombres);
                        user.setApellidos(apellidos);

                        UserService.getInstance().saveUser(user);
                    } else {
                        Log.w("ONSAVE USER", "CAMPOS LLENOS");
                    }
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }
            }
        });
        return view;
    }
}