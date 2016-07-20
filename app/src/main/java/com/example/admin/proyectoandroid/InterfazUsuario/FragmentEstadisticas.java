package com.example.admin.proyectoandroid.InterfazUsuario;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.R;
import com.example.admin.proyectoandroid.clsUsuario;


public class FragmentEstadisticas extends Fragment {

    clsUsuario usuario = new clsUsuario();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estadisticas, container, false);

        usuario = ((AplicacionPrincipal)getActivity().getApplication()).getUsuario();

        TextView tvValorNivel = (TextView) view.findViewById(R.id.valor_nivel);
        ProgressBar pbProgreso = (ProgressBar) view.findViewById(R.id.progresoExp);
        TextView tvValorFuerza = (TextView) view.findViewById(R.id.valor_fuerza);
        TextView tvValorDestreza = (TextView) view.findViewById(R.id.valor_destreza);
        TextView tvValorInteligencia = (TextView) view.findViewById(R.id.valor_inteligencia);

        tvValorNivel.setText(usuario.getNivel() + "");
        pbProgreso.setProgress(((AplicacionPrincipal)getActivity().getApplication()).porcentajeExp());
        tvValorFuerza.setText(usuario.getFuerza() + "");
        tvValorDestreza.setText(usuario.getDestreza() + "");
        tvValorInteligencia.setText(usuario.getInteligencia() + "");

        return view;
    }
}