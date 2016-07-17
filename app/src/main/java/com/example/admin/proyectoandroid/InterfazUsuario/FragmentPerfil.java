package com.example.admin.proyectoandroid.InterfazUsuario;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.InterfazUsuario.AlertDialogos.DialogoEditarEmail;
import com.example.admin.proyectoandroid.InterfazUsuario.AlertDialogos.DialogoEditarNombre;
import com.example.admin.proyectoandroid.InterfazUsuario.AlertDialogos.DialogoPerfil;
import com.example.admin.proyectoandroid.R;
import com.example.admin.proyectoandroid.clsUsuario;


public class FragmentPerfil extends Fragment {

    clsUsuario usuario = new clsUsuario();

    TextView tvNombre;
    TextView tvEmail;
    TextView tvMisionesFinalizadas;
    TextView tvMisionesIncompletas;
    TextView tvPreguntasSuperadas;
    TextView tvPreguntasFallidas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        usuario = ((AplicacionPrincipal)getActivity().getApplication()).getUsuario();

        tvNombre = (TextView) view.findViewById(R.id.texto_nombre);
        tvEmail = (TextView) view.findViewById(R.id.texto_email);
        tvMisionesFinalizadas = (TextView) view.findViewById(R.id.valor_misiones_finalizadas);
        tvMisionesIncompletas = (TextView) view.findViewById(R.id.valor_misiones_incompletas);
        tvPreguntasSuperadas = (TextView) view.findViewById(R.id.valor_preguntas_superadas);
        tvPreguntasFallidas = (TextView) view.findViewById(R.id.valor_preguntas_fallidas);

        tvNombre.setText(usuario.getNombre());
        tvEmail.setText(usuario.getEmail());
        tvMisionesFinalizadas.setText(usuario.getMisionesCompletas()+"");
        tvMisionesIncompletas.setText(usuario.getMisionesFallidas()+"");
        tvPreguntasSuperadas.setText(usuario.getPreguntasSuperadas()+"");
        tvPreguntasFallidas.setText(usuario.getPreguntasIncorrectas()+"");

        Button btnEditarNombre = (Button) view.findViewById(R.id.boton_editar_nombre);
        Button btnEditarEmail = (Button) view.findViewById(R.id.boton_editar_email);

        btnEditarNombre.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogoEditarNombre();
                    }
                }

        );

        btnEditarEmail.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogoEditarEmail();
                    }
                }

        );

        return view;
    }

    public void dialogoEditarEmail(){
        DialogoEditarEmail dialogoPersonalizado = new DialogoEditarEmail();
        dialogoPersonalizado.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        dialogoPersonalizado.show(getFragmentManager(), "personalizado");
        android.support.v4.app.Fragment frag = getFragmentManager().findFragmentByTag("personalizado");

        if (frag != null) {
            getFragmentManager().beginTransaction().remove(frag).commit();
        }

    }

    public void dialogoEditarNombre(){
        DialogoEditarNombre dialogoPersonalizado = new DialogoEditarNombre();
        dialogoPersonalizado.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        dialogoPersonalizado.show(getFragmentManager(), "personalizado");
        android.support.v4.app.Fragment frag = getFragmentManager().findFragmentByTag("personalizado");

        if (frag != null) {
            getFragmentManager().beginTransaction().remove(frag).commit();
        }

    }
}