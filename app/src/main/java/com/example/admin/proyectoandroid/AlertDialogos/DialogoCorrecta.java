package com.example.admin.proyectoandroid.AlertDialogos;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.proyectoandroid.R;

public class DialogoCorrecta extends DialogFragment  {

    public DialogoCorrecta() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogo_correcta, container);

        Button btnCorrecta = (Button) view.findViewById(R.id.aceptar_correcta);

        btnCorrecta.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Dialogo pregunta correcta", Toast.LENGTH_LONG).show();
                        dismiss();
                    }
                }

        );

        getDialog().getWindow().setGravity(Gravity.TOP);

        return view;
    }

}
