package com.example.admin.proyectoandroid.InterfazUsuario.AlertDialogos;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.R;

public class DialogoPerfil extends DialogFragment  {

    public DialogoPerfil() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogo_perfil, container);

        Button btnEntrar = (Button) view.findViewById(R.id.entrar_boton);
        Button btnSalir = (Button) view.findViewById(R.id.salir_boton);
        final EditText nombre = (EditText) view.findViewById(R.id.nombre_usuario);
        final EditText email = (EditText) view.findViewById(R.id.email_usuario);

        btnEntrar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String NuevoNombre = nombre.getText().toString();
                        String NuevoEmail = email.getText().toString();
                        if ((NuevoNombre != null) && !(NuevoNombre.equals(""))) {
                            if ((NuevoEmail != null) && !(NuevoEmail.equals("")) ) {
                                ((AplicacionPrincipal) getActivity().getApplication()).insertarUsuario(NuevoNombre, NuevoEmail);
                            } else {
                                ((AplicacionPrincipal) getActivity().getApplication()).insertarUsuario(NuevoNombre, "user@gmail.com");
                            }
                            Toast.makeText(getActivity(), "PERFIL CREADO", Toast.LENGTH_LONG).show();
                            dismiss();
                        } else {
                            Toast.makeText(getActivity(), "INGRESE UN NOMBRE DE PERFIL", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

        btnSalir.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                    }
                }
        );

        return view;

    }







}
