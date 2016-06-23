package com.example.admin.proyectoandroid.InterfazUsuario;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.R;


public class ListarUnLogro extends ActionBarActivity {
    private ImageView imgImagen;
    private TextView txtTitulo, txtContenido;
    String[] titulo;
    String[] contenido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_un_logro);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarLogro);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        Bundle  extras = getIntent().getExtras();
        final int position = extras.getInt("position");
        /*String tituloLogro = getResources().getStringArray(R.array.titulo_logros)[position];
        String estadoLogro = getResources().getStringArray(R.array.estado_logro)[position];*/

        /*INDICAR TITULO Y SUBTITULO
        if (ab != null) {
            ab.setTitle(tituloLogro);
            ab.setSubtitle("Estado : " + estadoLogro);
        }

        txtTitulo = (TextView) findViewById(R.id.tv_titulo_listarUnLogro);
        txtContenido = (TextView) findViewById(R.id.tv_contenido_ListarUnLogro);
        imgImagen = (ImageView) findViewById(R.id.iv_imagen_listarUnLogro);

        titulo = getResources().getStringArray(R.array.titulo_logros);
        contenido = getResources().getStringArray(R.array.contenido_logros);
        imgImagen.setImageResource(R.drawable.ic_logro_ejemplo);

        txtTitulo.setText(titulo[position]);
        txtContenido.setText(contenido[position]);*/

    }


}

