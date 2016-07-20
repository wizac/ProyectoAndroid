package com.example.admin.proyectoandroid.InterfazUsuario;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.R;
import com.example.admin.proyectoandroid.clsMision;

import java.util.ArrayList;


public class FragmentMisiones extends Fragment {

    ListViewAdapter adapter;
    ArrayList<clsMision> misiones = new ArrayList<clsMision>();
    String[] titulos;
    int[] imagenes;
    int[] etapas;
    int[] etapaActual;
    int[] progresos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_misiones, container, false);

        ListView lista = (ListView) rootView.findViewById(R.id.listView_listarMisiones);

        if(((AplicacionPrincipal)getActivity().getApplication()).getMisionesActivas().size() == 0){
            ((AplicacionPrincipal)getActivity().getApplication()).llenarMisionesDiarias();
        }

        misiones = ((AplicacionPrincipal)getActivity().getApplication()).getMisionesActivas();
        titulos = new String[misiones.size()];
        imagenes = new int[misiones.size()];
        etapas = new int[misiones.size()];
        etapaActual = new int[misiones.size()];
        progresos = new int[misiones.size()];

        for(int i = 0; i < misiones.size(); i++)
        {
            titulos[i] = misiones.get(i).getTitulo();
            etapas[i] = misiones.get(i).getProgreso();
            etapaActual[i] = misiones.get(i).getProgresoActual();
            progresos[i] = ((AplicacionPrincipal)getActivity().getApplication()).porcentajeProgreso(misiones.get(i));
            if(progresos[i] >= 30 && progresos[i] < 50){
                imagenes[i] = R.drawable.estrellas_1llenas_2vacias;
            }
            else if(progresos[i] >= 50 && progresos[i] < 80){
                imagenes[i] = R.drawable.estrellas_2llenas_1vacias;
            }
            else if(progresos[i] == 100){
                imagenes[i] = R.drawable.estrellas_3llenas_0vacias;
            }
            else{
                imagenes[i] = R.drawable.estrellas_0llenas_3vacias;
            }
        }

        adapter = new ListViewAdapter(getContext(), titulos, imagenes, progresos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(etapas[position] != etapaActual[position]) {
                    Intent i = new Intent(getActivity(), ListarUnaMision.class);
                    i.putExtra("position", position);
                    startActivity(i);
                }
            }
        });
        return rootView;
    }

    public class ListViewAdapter extends BaseAdapter {

        Context context;
        String[] titulos;
        int[] imagenes;
        int[] progresos;
        LayoutInflater inflater;

        public ListViewAdapter(Context context, String[] titulos, int[] imagenes, int[] progresos) {
            this.context = context;
            this.titulos = titulos;
            this.imagenes = imagenes;
            this.progresos = progresos;
        }

        @Override
        public int getCount() {
            return titulos.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            TextView tvTitulo;
            ImageView ivEstrellas;
            ProgressBar pbProgreso;
            LinearLayout linearL_una_mision;
            TextView tvTituloProgreso;
            ImageView ivCompletado;

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View itemView = inflater.inflate(R.layout.lista_personalizada_misiones, parent, false);

            tvTitulo = (TextView) itemView.findViewById(R.id.tv_titulo_lista_personalizada_mision);
            ivEstrellas = (ImageView) itemView.findViewById(R.id.imagen_lista_personalizada_mision);
            pbProgreso = (ProgressBar) itemView.findViewById(R.id.progresoMision);
            linearL_una_mision = (LinearLayout) itemView.findViewById(R.id.una_mision);
            ivCompletado = (ImageView) itemView.findViewById(R.id.imagen_completado);

            ivEstrellas.setImageResource(imagenes[position]);
            tvTitulo.setText(titulos[position]);
            pbProgreso.setProgress(progresos[position]);

            if(etapas[position] == etapaActual[position]){
                linearL_una_mision.setBackgroundColor(Color.parseColor("#E6E6E6"));
                ivCompletado.setVisibility(View.VISIBLE);
            }

            return itemView;
        }
    }
}
