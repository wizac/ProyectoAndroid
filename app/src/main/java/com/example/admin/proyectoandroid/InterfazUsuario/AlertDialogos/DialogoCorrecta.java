package com.example.admin.proyectoandroid.InterfazUsuario.AlertDialogos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.proyectoandroid.InterfazUsuario.FragmentCuenta;
import com.example.admin.proyectoandroid.InterfazUsuario.FragmentInicio;
import com.example.admin.proyectoandroid.InterfazUsuario.MainActivity;
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
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("ItemMenu",0);
                        intent.putExtra("PosicionTab",1);
                        startActivity(intent);
                        dismiss();
                    }
                }

        );

        getDialog().getWindow().setGravity(Gravity.TOP);

        return view;
    }

}
