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
public class ListarUnaMision extends ActionBarActivity {
    private ImageView imgImagen;
    private TextView txtTitulo, txtContenido;
    String[] titulo;
    String[] contenido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_una_mision);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMision);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        Bundle  extras = getIntent().getExtras();
        int idMision= extras.getInt("idMision");
        final int position = extras.getInt("position");
        String tituloMision = getResources().getStringArray(R.array.titulo_misiones)[position];
        String dificultadMision = getResources().getStringArray(R.array.dificultad_misiones)[position];

        /*INDICAR TITULO Y SUBTITULO*/
        if (ab != null) {
            ab.setTitle(tituloMision);
            ab.setSubtitle("Dificultad: " + dificultadMision);
        }

        txtTitulo = (TextView) findViewById(R.id.tv_titulo_listarUnaMision);
        txtContenido = (TextView) findViewById(R.id.tv_contenido_ListarUnaMision);
        imgImagen = (ImageView) findViewById(R.id.iv_imagen_listarUnaMision);

        titulo = getResources().getStringArray(R.array.titulo_misiones);
        contenido = getResources().getStringArray(R.array.contenido_misiones);
        imgImagen.setImageResource(R.drawable.bob);

        txtTitulo.setText(titulo[position]);
        txtContenido.setText(contenido[position]);

    }


}

