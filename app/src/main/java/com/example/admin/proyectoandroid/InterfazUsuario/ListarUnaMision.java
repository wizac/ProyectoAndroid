package com.example.admin.proyectoandroid.InterfazUsuario;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.proyectoandroid.AlertDialogos.DialogoCompleta;
import com.example.admin.proyectoandroid.AlertDialogos.DialogoPerfil;
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
    private Button btnSiguienteEtapa;
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

        /*INDICAR TITULO, SUBTITULO Y AGREGAR ICONO HOME ATRAS*/
        if (ab != null) {
            ab.setTitle(tituloMision);
            ab.setSubtitle("Dificultad: " + dificultadMision);
            ab.setHomeAsUpIndicator(R.drawable.icono_atras_2);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        txtContenido = (TextView) findViewById(R.id.tv_contenido_ListarUnaMision);
        txtContenido.setText(contenidos[position]);

        btnSiguienteEtapa = (Button) findViewById(R.id.boton_siguiente_etapa);
        btnSiguienteEtapa.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogoCompleta();
                    }
                }

        );

    }

    // Metodo para asignar el menu al actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void dialogoCompleta(){
        DialogoCompleta dialogoPersonalizado = new DialogoCompleta();
        dialogoPersonalizado.show(getSupportFragmentManager(), "personalizado");
        dialogoPersonalizado.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogTransparente);
        dialogoPersonalizado.setCancelable(false);
        android.app.Fragment frag = getFragmentManager().findFragmentByTag("personalizado");

        if (frag != null) {
            getFragmentManager().beginTransaction().remove(frag).commit();
        }
    }

}

