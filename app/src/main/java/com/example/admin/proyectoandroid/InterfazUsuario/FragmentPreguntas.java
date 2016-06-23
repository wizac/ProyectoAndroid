package com.example.admin.proyectoandroid.InterfazUsuario;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.R;
import com.example.admin.proyectoandroid.clsPregunta;

import java.util.ArrayList;


public class FragmentPreguntas extends Fragment {

    ListViewAdapter adapter;
    ArrayList<clsPregunta> preguntas = new ArrayList<clsPregunta>();
    int[] imagenes;
    String[] genero;
    int[] fuerza;
    int[] destreza;
    int[] inteligencia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_preguntas, container, false);

        ListView lista = (ListView) rootView.findViewById(R.id.listView_listarPreguntas);

        if(((AplicacionPrincipal)getActivity().getApplication()).getPreguntasActivas().size() == 0){
            ((AplicacionPrincipal)getActivity().getApplication()).llenarPreguntasDiarias();
        }

        preguntas = ((AplicacionPrincipal)getActivity().getApplication()).llenarPreguntasDiarias();
        imagenes = new int[preguntas.size()];
        genero = new String[preguntas.size()];
        fuerza =  new int[preguntas.size()];
        destreza =  new int[preguntas.size()];
        inteligencia =  new int[preguntas.size()];

        for(int i = 0; i < preguntas.size(); i++)
        {
            genero[i] = preguntas.get(i).getCategoria();
            fuerza[i] = preguntas.get(i).getFuerza();
            destreza[i] = preguntas.get(i).getDestreza();
            inteligencia[i] = preguntas.get(i).getInteligencia();
            

        }

        adapter = new ListViewAdapter(getContext(), titulos, imagenes, genero);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ListarUnaPregunta.class);
                i.putExtra("position", position);
                startActivity(i);


            }
        });
        return rootView;
    }

    public class ListViewAdapter extends BaseAdapter {
        // Declare Variables
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

            // Declare Variables
            TextView txtTitle;
            ImageView imgImg;
            ProgressBar pbProgreso;

            //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View itemView = inflater.inflate(R.layout.lista_personalizada_preguntas, parent, false);

            imgImg = (ImageView) itemView.findViewById(R.id.imagen_lista_personalizada_pregunta);
            txtTitle = (TextView) itemView.findViewById(R.id.tv_titulo_lista_personalizada_pregunta);
            pbProgreso = (ProgressBar) itemView.findViewById(R.id.progresoPregunta);

            imgImg.setImageResource(imagenes[position]);
            txtTitle.setText(titulos[position]);
            pbProgreso.setProgress(progresos[position]);

            return itemView;
        }
    }
}
