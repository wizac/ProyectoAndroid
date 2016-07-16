package com.example.admin.proyectoandroid.InterfazUsuario;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.InterfazUsuario.AlertDialogos.DialogoPerfil;
import com.example.admin.proyectoandroid.R;
import com.example.admin.proyectoandroid.clsUsuario;


public class FragmentPerfil extends Fragment {

    clsUsuario usuario = new clsUsuario();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        usuario = ((AplicacionPrincipal)getActivity().getApplication()).getUsuario();

        TextView tvNombre = (TextView) view.findViewById(R.id.texto_nombre);
        TextView tvEmail = (TextView) view.findViewById(R.id.texto_email);
        TextView tvMisionesFinalizadas = (TextView) view.findViewById(R.id.valor_misiones_finalizadas);
        TextView tvMisionesIncompletas = (TextView) view.findViewById(R.id.valor_misiones_incompletas);
        TextView tvPreguntasSuperadas = (TextView) view.findViewById(R.id.valor_preguntas_superadas);
        TextView tvPreguntasFallidas = (TextView) view.findViewById(R.id.valor_preguntas_fallidas);

        tvNombre.setText(usuario.getNombre());

        Button btnEditarNombre = (Button) view.findViewById(R.id.boton_editar_nombre);
        Button btnEditarEmail = (Button) view.findViewById(R.id.boton_editar_email);


        return view;
    }

    /*public void dialogoEditarNombre(){
        DialogoPerfil dialogoPersonalizado = new DialogoPerfil();
        dialogoPersonalizado.show(getFragmentManager(), "personalizado");
        dialogoPersonalizado.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        dialogoPersonalizado.setCancelable(false);
        android.app.Fragment frag = getActivity().getFragmentManager().findFragmentByTag("personalizado");

        if (frag != null) {
            getFragmentManager().beginTransaction().remove(frag).commit();
        }
    }*/
}