package com.example.admin.proyectoandroid.InterfazUsuario;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.proyectoandroid.R;

/**
 * Created by Admin on 12/1/2016.
 */
public class ListarUnaPregunta extends ActionBarActivity {
    private ImageView imgImagen;
    private TextView txtTitulo, txtContenido;
    String[] titulo;
    String[] contenido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_una_pregunta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPregunta);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        Bundle  extras = getIntent().getExtras();
        final int position = extras.getInt("position");
        String tituloPregunta = getResources().getStringArray(R.array.titulo_preguntas)[position];
        String generoPregunta = getResources().getStringArray(R.array.genero_pregunta)[position];

        /*INDICAR TITULO Y SUBTITULO*/
        if (ab != null) {
            ab.setTitle(tituloPregunta);
            ab.setSubtitle("Categoria : " + generoPregunta);
        }

        txtTitulo = (TextView) findViewById(R.id.tv_titulo_listarUnaPregunta);
        txtContenido = (TextView) findViewById(R.id.tv_contenido_ListarUnaPregunta);
        imgImagen = (ImageView) findViewById(R.id.iv_imagen_listarUnaPregunta);

        titulo = getResources().getStringArray(R.array.titulo_preguntas);
        contenido = getResources().getStringArray(R.array.contenido_misiones);
        imgImagen.setImageResource(R.drawable.preguntas_categoria_arte);

        txtTitulo.setText(titulo[position]);
        txtContenido.setText(contenido[position]);

    }


}

