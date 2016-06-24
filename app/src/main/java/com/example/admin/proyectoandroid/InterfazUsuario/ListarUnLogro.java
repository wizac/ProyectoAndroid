package com.example.admin.proyectoandroid.InterfazUsuario;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.R;
import com.example.admin.proyectoandroid.clsLogro;

import java.util.ArrayList;


public class ListarUnLogro extends ActionBarActivity {
    private ImageView ivImagen;
    private TextView tvTitulo, tvDescripcion;
    ArrayList<clsLogro> logros = new ArrayList<clsLogro>();
    String[] titulos;
    int[] imagenes;
    String[] estados;
    String[] descripciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_un_logro);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarLogro);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        Bundle  extras = getIntent().getExtras();
        final int position = extras.getInt("position");

        logros =((AplicacionPrincipal) getApplication()).getLogros();
        titulos = new String[logros.size()];
        imagenes = new int[logros.size()];
        estados = new String[logros.size()];
        descripciones = new String[logros.size()];


        for(int i = 0; i < logros.size(); i++){
            titulos[i] = logros.get(i).getNombre();
            estados[i] = logros.get(i).getEstado();
            descripciones[i] = logros.get(i).getDescripcion();
            imagenes[i] = getResources().getIdentifier(logros.get(i).getNombreimagen().toString() , "drawable",  getPackageName());
        }

        /*INDICAR TITULO Y SUBTITULO*/
        if (ab != null) {
            ab.setTitle(titulos[position]);
            ab.setSubtitle("Estado : " + estados[position]);
        }

        tvTitulo = (TextView) findViewById(R.id.tv_titulo_listarUnLogro);
        tvDescripcion = (TextView) findViewById(R.id.tv_contenido_ListarUnLogro);
        ivImagen = (ImageView) findViewById(R.id.iv_imagen_listarUnLogro);

        tvTitulo.setText(titulos[position]);
        tvDescripcion.setText(descripciones[position]);
        ivImagen.setImageResource(imagenes[position]);

    }


}

