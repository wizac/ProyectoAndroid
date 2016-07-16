package com.example.admin.proyectoandroid.InterfazUsuario.AlertDialogos;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.R;

public class DialogoEditarEmail extends DialogFragment  {

    public DialogoEditarEmail() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogo_editar, container);

        Button btnguardar = (Button) view.findViewById(R.id.boton_guardar);
        Button btncancelar = (Button) view.findViewById(R.id.boton_cancelar);
        final TextView tituloDialog = (TextView) view.findViewById(R.id.tv_titulo_dialog_edit);
        final EditText email = (EditText) view.findViewById(R.id.campo_edit);
        tituloDialog.setText("Puedes modificar el email de tu perfil");
        email.setHint(((AplicacionPrincipal) getActivity().getApplication()).getUsuario().getEmail());

        btnguardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((AplicacionPrincipal) getActivity().getApplication()).getUsuario().setEmail(email.getText().toString());
                        dismiss();
                    }
                }

        );

        btncancelar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }

        );

        return view;
    }

}

