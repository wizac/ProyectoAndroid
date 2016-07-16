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

public class DialogoEditarNombre extends DialogFragment  {

    public DialogoEditarNombre() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogo_editar, container);

        Button btnguardar = (Button) view.findViewById(R.id.boton_guardar);
        Button btncancelar = (Button) view.findViewById(R.id.boton_cancelar);
        final TextView tituloDialog = (TextView) view.findViewById(R.id.tv_titulo_dialog_edit);
        final EditText nombre = (EditText) view.findViewById(R.id.campo_edit);
        tituloDialog.setText("Puedes modificar el nombre de tu perfil");
        nombre.setHint(((AplicacionPrincipal) getActivity().getApplication()).getUsuario().getNombre());

        btnguardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }

        );

        btncancelar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((AplicacionPrincipal) getActivity().getApplication()).getUsuario().setNombre(nombre.getText().toString());
                        dismiss();
                    }
                }

        );

        return view;
    }

}

