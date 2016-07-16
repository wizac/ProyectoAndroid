package com.example.admin.proyectoandroid.InterfazUsuario.AlertDialogos;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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

        Button perfil = (Button) view.findViewById(R.id.entrar_boton);
        final EditText usuario = (EditText) view.findViewById(R.id.nombre_usuario);
        final EditText email = (EditText) view.findViewById(R.id.email_usuario);

        perfil.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((AplicacionPrincipal)getActivity().getApplication()).insertarUsuario(usuario.getText().toString(), email.getText().toString());
                        if(((AplicacionPrincipal)getActivity().getApplication()).getUsuario() != null){
                            Toast.makeText(getActivity(), "PERFIL CREADO", Toast.LENGTH_LONG).show();
                        }
                        dismiss();
                    }
                }

        );

        return view;
    }

}
