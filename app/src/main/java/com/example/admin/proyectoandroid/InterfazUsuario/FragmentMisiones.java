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
import android.widget.Toast;

import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.clsMision;
import com.example.admin.proyectoandroid.R;

import java.util.ArrayList;


public class FragmentMisiones extends Fragment {

    ListViewAdapter adapter;
    AplicacionPrincipal AP;
    ArrayList<clsMision> misiones = AP.llenarMisionesDiarias();
    int cantidadMisiones = misiones.size();

    String[] titulos = new String[cantidadMisiones];
    int[] imagenes = new int[cantidadMisiones];
    int[] progresos = new int[cantidadMisiones];



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_misiones, container, false);

        ListView lista = (ListView) rootView.findViewById(R.id.listView_listarMisiones);
        misiones = AP.llenarMisionesDiarias();

        for(int i = 0; i < cantidadMisiones; i++)
        {
            titulos[i] = ((clsMision)misiones.get(i)).getTitulo();
            imagenes[i] = R.drawable.bob;
            progresos[i] = ((clsMision)misiones.get(i)).getProgreso();
        }



        adapter = new ListViewAdapter(getContext(), titulos, imagenes, progresos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ListarUnaMision.class);
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

            View itemView = inflater.inflate(R.layout.lista_personalizada_misiones, parent, false);

            imgImg = (ImageView) itemView.findViewById(R.id.imagen_lista_personalizada_mision);
            txtTitle = (TextView) itemView.findViewById(R.id.tv_titulo_lista_personalizada_mision);
            pbProgreso = (ProgressBar) itemView.findViewById(R.id.progresoMision);

            imgImg.setImageResource(imagenes[position]);
            txtTitle.setText(titulos[position]);
            pbProgreso.setProgress(progresos[position]);

            return itemView;
        }
    }
}
