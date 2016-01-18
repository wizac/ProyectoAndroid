package com.example.admin.proyectoandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Admin on 12/1/2016.
 */
public class ListarMisiones extends ActionBarActivity {

    int[] imagenModalidadFacil= {
            R.drawable.modalidad_facil_1,
            R.drawable.modalidad_facil_2,
            R.drawable.modalidad_facil_3,
            R.drawable.modalidad_facil_4,
            R.drawable.modalidad_facil_5,
    };

    int[] imagenModalidadNormal = {
            R.drawable.modalidad_normal_1,
            R.drawable.modalidad_normal_2,
            R.drawable.modalidad_normal_3,
    };

    int[] imagenModalidadDificil = {
            R.drawable.modalidad_dificil_1,
            R.drawable.modalidad_dificil_2,
            R.drawable.modalidad_dificil_3,
            R.drawable.modalidad_dificil_4,
    };



    String[] titulo;
    String[] contenido;

    private ListView lista;
    ListViewAdapter adapter;

    int currentViewPager;
    String nombreModalidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_misiones);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);  //ir atras

        Bundle extras = getIntent().getExtras();
        currentViewPager = extras.getInt("currentViewPager");
        nombreModalidad = extras.getString("nombreModalidad");


        /**INDICAR TITULO **/
        actionBar.setTitle(nombreModalidad);

        lista = (ListView) findViewById(R.id.listView_listarMisiones);
        switch (currentViewPager){
            case 0: //Modalidad Facil
                titulo = getResources().getStringArray(R.array.modalidad_facil_titulo);
                contenido = getResources().getStringArray(R.array.modalidad_facil_contenido);
                adapter = new ListViewAdapter(this, imagenModalidadFacil, titulo, contenido);
                break;
            case 1: //Modalidad Normal
                titulo = getResources().getStringArray(R.array.modalidad_normal_titulo);
                contenido = getResources().getStringArray(R.array.modalidad_normal_contenido);
                adapter = new ListViewAdapter(this, imagenModalidadNormal, titulo, contenido);
                break;

            case 2: //Modalidad Dificil
                titulo = getResources().getStringArray(R.array.modalidad_dificil_titulo);
                contenido = getResources().getStringArray(R.array.modalidad_dificil_contenido);
                adapter = new ListViewAdapter(this, imagenModalidadDificil, titulo, contenido);
                break;


            default:
                Toast.makeText(getApplicationContext(), "no esta cargado, pronto lo estará", Toast.LENGTH_SHORT).show();
        }
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ListarUnaMision.class);
                i.putExtra("idMision",currentViewPager);
                i.putExtra("position", position);
                i.putExtra("nombreModalidad", nombreModalidad);
                i.putExtra("nombreSubMision", titulo[position]);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


            }
        });
    }


    /******************* LISTVIEW ADAPTER **************************/

    public class ListViewAdapter extends BaseAdapter {
        // Declare Variables
        Context context;
        int[] imagenes;
        String[] titulos;
        String[] contenido;
        LayoutInflater inflater;

        public ListViewAdapter(Context context, int[] imagenes, String[] titulos, String[] contenido ) {
            this.context = context;
            this.imagenes = imagenes;
            this.titulos = titulos;
            this.contenido = contenido;
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
            ImageView imgImg;
            TextView txtTitle;
            TextView txtContenido;

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View itemView = inflater.inflate(R.layout.lista_personalizada, parent, false);

            imgImg = (ImageView) itemView.findViewById(R.id.imagen_lista_personalizada_mision);
            txtTitle = (TextView) itemView.findViewById(R.id.tv_titulo_lista_personalizada_mision);
            txtContenido = (TextView) itemView.findViewById(R.id.tv_contenido_lista_personalizada_mision);

            imgImg.setImageResource(imagenes[position]);
            txtTitle.setText(titulos[position]);
            txtContenido.setText(contenido[position]);

            return itemView;
        }
    }
}
