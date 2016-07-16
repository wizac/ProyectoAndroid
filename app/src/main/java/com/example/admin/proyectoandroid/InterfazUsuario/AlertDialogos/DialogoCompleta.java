package com.example.admin.proyectoandroid.InterfazUsuario.AlertDialogos;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.proyectoandroid.R;

public class DialogoCompleta extends DialogFragment  {

    public DialogoCompleta() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogo_completa, container);

        Button btnCompleta = (Button) view.findViewById(R.id.aceptar_completa);

        btnCompleta.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().onBackPressed();
                        Toast.makeText(getActivity(), "Dialogo mision completa", Toast.LENGTH_LONG).show();
                        dismiss();
                    }
                }

        );

        getDialog().getWindow().setGravity(Gravity.TOP);

        return view;
    }

}

