package com.example.admin.proyectoandroid.InterfazUsuario;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.proyectoandroid.R;

/**
 * Created by Admin on 12/1/2016.
 */
public class ListarUnaMision extends ActionBarActivity {
/*    private ImageView imgImagen;
    private TextView txtTitulo, txtContenido;
    String[] titulo;
    String[] contenido;

    private int[] imagenModalidadFacil= {
            R.drawable.modalidad_facil_1,
            R.drawable.modalidad_facil_2,
            R.drawable.modalidad_facil_3,
            R.drawable.modalidad_facil_4,
            R.drawable.modalidad_facil_5
    };

    private int[] imagenModalidadNormal = {
            R.drawable.modalidad_normal_1,
            R.drawable.modalidad_normal_2,
            R.drawable.modalidad_normal_3
    };

    private int[] imagenModalidadDificil = {
            R.drawable.modalidad_dificil_1,
            R.drawable.modalidad_dificil_2,
            R.drawable.modalidad_dificil_3,
            R.drawable.modalidad_dificil_4
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_una_mision);

       /* ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle  extras = getIntent().getExtras();
        int idMision= extras.getInt("idMision");
        final int position = extras.getInt("position");
        String nombreModalidad = extras.getString("nombreModalidad");
        String nombreSubMision = extras.getString("nombreSubMision");


        INDICAR TITULO Y SUBTITULO
        actionBar.setTitle(nombreModalidad);
        actionBar.setSubtitle(nombreSubMision);

        txtTitulo = (TextView) findViewById(R.id.tv_titulo_listarUnaMision);
        txtContenido = (TextView) findViewById(R.id.tv_contenido_ListarUnaMision);
        imgImagen = (ImageView) findViewById(R.id.iv_imagen_listarUnaMision);

        switch (idMision){
            case 0: //Modalidad Facil
                titulo = getResources().getStringArray(R.array.modalidad_facil_titulo);
                contenido = getResources().getStringArray(R.array.modalidad_facil_contenido_completo);
                imgImagen.setImageResource(imagenModalidadFacil[position]);
                break;
            case 1: //Modalidad Normal
                titulo = getResources().getStringArray(R.array.modalidad_normal_titulo);
                contenido = getResources().getStringArray(R.array.modalidad_normal_contenido_completo);
                imgImagen.setImageResource(imagenModalidadNormal[position]);
                break;

            case 2: //Modalidad Dificil
                titulo = getResources().getStringArray(R.array.modalidad_dificil_titulo);
                contenido = getResources().getStringArray(R.array.modalidad_dificil_contenido_completo);
                imgImagen.setImageResource(imagenModalidadDificil[position]);
                break;


            default:
                Toast.makeText(getApplicationContext(), "no esta cargado, pronto lo estará", Toast.LENGTH_SHORT).show();
        }
        txtTitulo.setText(titulo[position]);
        txtContenido.setText(contenido[position]);
        */
    }
}

