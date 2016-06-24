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
import com.example.admin.proyectoandroid.clsLogro;

import java.util.ArrayList;


public class FragmentLogros extends Fragment {

    ListViewAdapter adapter;

    ArrayList<clsLogro> logros = new ArrayList<clsLogro>();
    String[] titulos;
    int[] imagenes;
    String[] estados;
    String[] descripciones;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_logros, container, false);

        ListView lista = (ListView) rootView.findViewById(R.id.listView_listarLogros);

        logros =((AplicacionPrincipal) getActivity().getApplication()).getLogros();
        titulos = new String[logros.size()];
        imagenes = new int[logros.size()];
        estados = new String[logros.size()];
        descripciones = new String[logros.size()];

        for(int i = 0; i < logros.size(); i++){
            titulos[i] = logros.get(i).getNombre();
            estados[i] = logros.get(i).getEstado();
            descripciones[i] = logros.get(i).getDescripcion();
            imagenes[i] = getResources().getIdentifier(logros.get(i).getNombreimagen().toString() , "drawable",  getActivity().getApplication().getPackageName());
        }

        adapter = new ListViewAdapter(getContext(), titulos, imagenes, estados, descripciones);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ListarUnLogro.class);
                i.putExtra("position", position);
                startActivity(i);


            }
        });
        return rootView;
    }
    public class ListViewAdapter extends BaseAdapter {

        Context context;
        String[] titulos;
        int[] imagenes;
        String[] estados;
        String[] descripciones;

        LayoutInflater inflater;

        public ListViewAdapter(Context context, String[] titulos, int[] imagenes, String[] estados, String[] descripciones) {
            this.context = context;
            this.titulos = titulos;
            this.imagenes = imagenes;
            this.estados = estados;
            this.descripciones = descripciones;
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
            ImageView ivImagen;
            TextView tvEstado;
            TextView tvDescripcion;

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View itemView = inflater.inflate(R.layout.lista_personalizada_logros, parent, false);

            ivImagen = (ImageView) itemView.findViewById(R.id.imagen_lista_personalizada_logro);
            tvTitulo = (TextView) itemView.findViewById(R.id.tv_titulo_logro);
            tvEstado = (TextView) itemView.findViewById(R.id.tv_estado_logro);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tv_descripcion_logro);
            ivImagen.setImageResource(imagenes[position]);
            tvTitulo.setText(titulos[position]);
            tvEstado.setText(estados[position]);
            tvDescripcion.setText(descripciones[position]);

            return itemView;
        }
    }
}
