package com.example.admin.proyectoandroid.InterfazUsuario;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.R;
import com.example.admin.proyectoandroid.clsMision;

import java.util.ArrayList;

/**
 * Created by Admin on 12/1/2016.
 */
public class ListarUnaMision extends ActionBarActivity {

    ArrayList<clsMision> misiones = new ArrayList<clsMision>();
    private TextView txtContenido;
    String[] titulos;
    String[] contenidos;


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

        misiones = ((AplicacionPrincipal)getApplication()).getMisionesActivas();
        titulos = new String[misiones.size()];
        contenidos = new String[misiones.size()];

        for(int i = 0; i < misiones.size(); i++)
        {
            titulos[i] = ((clsMision)misiones.get(i)).getTitulo();
            contenidos[i] = ((clsMision)misiones.get(i)).getDescripcion();
        }
        String tituloMision = titulos[position];
        // CORREGIR
        String dificultadMision = "normal";

        /*INDICAR TITULO Y SUBTITULO*/
        if (ab != null) {
            ab.setTitle(tituloMision);
            ab.setSubtitle("Dificultad: " + dificultadMision);
        }

        txtContenido = (TextView) findViewById(R.id.tv_contenido_ListarUnaMision);
        txtContenido.setText(contenidos[position]);

    }


}

