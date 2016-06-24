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
import com.example.admin.proyectoandroid.R;
import com.example.admin.proyectoandroid.clsPregunta;

import java.util.ArrayList;


public class FragmentPreguntas extends Fragment {

    ListViewAdapter adapter;
    ArrayList<clsPregunta> preguntas = new ArrayList<clsPregunta>();
    int[] imagenes;
    String[] categoria;
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

        preguntas = ((AplicacionPrincipal)getActivity().getApplication()).getPreguntasActivas();
        imagenes = new int[preguntas.size()];
        categoria = new String[preguntas.size()];
        fuerza =  new int[preguntas.size()];
        destreza =  new int[preguntas.size()];
        inteligencia =  new int[preguntas.size()];

        for(int i = 0; i < preguntas.size(); i++)
        {
            categoria[i] = preguntas.get(i).getCategoria();
            fuerza[i] = preguntas.get(i).getFuerza();
            destreza[i] = preguntas.get(i).getDestreza();
            inteligencia[i] = preguntas.get(i).getInteligencia();

            if(preguntas.get(i).getCategoria().toString().compareTo("arte") == 0){
                imagenes[i] = R.drawable.preguntas_categoria_arte;
            }
            else if(preguntas.get(i).getCategoria().toString().compareTo("entretenimiento") == 0){
                imagenes[i] = R.drawable.preguntas_categoria_entretenimiento;
            }
            else if(preguntas.get(i).getCategoria().toString().compareTo("deporte") == 0){
                imagenes[i] = R.drawable.preguntas_categoria_deporte;
            }
            else if(preguntas.get(i).getCategoria().toString().compareTo("geografia") == 0){
                imagenes[i] = R.drawable.preguntas_categoria_geografia;
            }
            else if(preguntas.get(i).getCategoria().toString().compareTo("ciencia") == 0){
                imagenes[i] = R.drawable.preguntas_categoria_ciencia;
            }
            else if(preguntas.get(i).getCategoria().compareTo("historia") == 0){
                imagenes[i] = R.drawable.preguntas_categoria_historia;
            }
            else{
                imagenes[i] = R.drawable.preguntas_categoria_entretenimiento;
            }

        }

        //Toast.makeText(getContext(),"cantidad: "+preguntas.size(),Toast.LENGTH_LONG).show();

        adapter = new ListViewAdapter(getContext(), categoria, imagenes, fuerza, destreza, inteligencia);
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
        String[] genero;
        int[] imagenes;
        int[] fuerza;
        int[] destreza;
        int[] inteligencia;
        LayoutInflater inflater;

        public ListViewAdapter(Context context, String[] genero, int[] imagenes, int[] fuerza, int[] destreza, int[] inteligencia) {
            this.context = context;
            this.genero = genero;
            this.imagenes = imagenes;
            this.fuerza = fuerza;
            this.destreza = destreza;
            this.inteligencia = inteligencia;
        }

        @Override
        public int getCount() {
            return genero.length;
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
            TextView tvCategoria;
            ImageView ivImagen;
            TextView tvFuerza;
            TextView tvDestreza;
            TextView tvInteligencia;

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View itemView = inflater.inflate(R.layout.lista_personalizada_preguntas, parent, false);

            ivImagen = (ImageView) itemView.findViewById(R.id.imagen_lista_personalizada_pregunta);
            tvCategoria = (TextView) itemView.findViewById(R.id.tv_categoria_lista_personalizada_pregunta);
            tvFuerza = (TextView) itemView.findViewById(R.id.tv_fuerza);
            tvInteligencia = (TextView) itemView.findViewById(R.id.tv_inteligencia);
            tvDestreza = (TextView) itemView.findViewById(R.id.tv_destreza);

            ivImagen.setImageResource(imagenes[position]);
            tvCategoria.setText(categoria[position]);
            tvFuerza.setText("Fuerza: +"+fuerza[position]);
            tvDestreza.setText("Destreza: +" + destreza[position]);
            tvInteligencia.setText("Inteligencia: +"+inteligencia[position]);

            return itemView;
        }
    }
}
