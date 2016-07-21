package com.example.admin.proyectoandroid.InterfazUsuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.proyectoandroid.InterfazUsuario.AlertDialogos.DialogoCompleta;
import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.R;
import com.example.admin.proyectoandroid.clsMision;

import java.util.ArrayList;

/**
 * Created by Admin on 12/1/2016.
 */
public class ListarUnaMision extends ActionBarActivity {

    ArrayList<clsMision> misiones = new ArrayList<clsMision>();
    int position;
    private TextView tvContenido, tvEtapa;
    private Button btnSiguienteEtapa, btnSalir;
    String[] titulos;
    String[] contenidos;
    String[] dificultades;
    int[] etapas, etapaActual, etapaVolatil;
    int contadorClicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_una_mision);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMision);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        Bundle  extras = getIntent().getExtras();
        position = extras.getInt("position");

        misiones = ((AplicacionPrincipal)getApplication()).getMisionesActivas();
        titulos = new String[misiones.size()];
        contenidos = new String[misiones.size()];
        dificultades = new String[misiones.size()];
        etapas = new int[misiones.size()];
        etapaActual = new int[misiones.size()];
        etapaVolatil = new int[misiones.size()];

        for(int i = 0; i < misiones.size(); i++)
        {
            titulos[i] = ((clsMision)misiones.get(i)).getTitulo();
            contenidos[i] = ((clsMision)misiones.get(i)).getDescripcion();
            dificultades[i] =  ((clsMision)misiones.get(i)).getDificultad();
            etapas[i] =  ((clsMision)misiones.get(i)).getProgreso();
            etapaActual[i] = ((clsMision)misiones.get(i)).getProgresoActual();
            etapaVolatil[i] = ((clsMision)misiones.get(i)).getProgresoActual();
        }
        String tituloMision = titulos[position];
        String dificultadMision = dificultades[position];

        /*INDICAR TITULO, SUBTITULO Y AGREGAR ICONO HOME ATRAS*/
        if (ab != null) {
            ab.setTitle(tituloMision);
            ab.setSubtitle("Dificultad: " + dificultadMision);
            ab.setHomeAsUpIndicator(R.drawable.icono_atras_2);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        btnSiguienteEtapa = (Button) findViewById(R.id.boton_siguiente_etapa);
        btnSalir = (Button) findViewById(R.id.boton_salir);
        tvContenido = (TextView) findViewById(R.id.tv_contenido_ListarUnaMision);
        tvEtapa = (TextView) findViewById(R.id.texto_etapas);

        tvContenido.setText(contenidos[position]);
        tvEtapa.setText("Etapa "+etapaActual[position]+"/"+etapas[position]);
        cambiarTexto();

        btnSiguienteEtapa.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(etapaVolatil[position] < etapas[position]) {
                            etapaVolatil[position]++;
                            contadorClicks++;
                            tvEtapa.setText("Etapa " + etapaVolatil[position] + "/" + etapas[position]);
                            cambiarTexto();
                            if (etapaVolatil[position] == etapas[position]) {
                                aumentarProgresoMision();
                                dialogoCompleta();
                            }
                        }
                    }
                }

        );

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAtras();
            }
        });

    }

    public void cambiarTexto(){
        if(etapas[position] - 1 == etapaVolatil[position]){
            btnSiguienteEtapa.setText("COMPLETAR MISION");
        }
    }

    public void aumentarProgresoMision(){
        ((AplicacionPrincipal)getApplication()).aumentarProgreso(misiones.get(position).getId(), contadorClicks);
    }

    public void irAtras(){
        if(etapaActual[position] != etapaVolatil[position]) {
            aumentarProgresoMision();
            Intent intent = new Intent(getApplication(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("ItemMenu", 0);
            intent.putExtra("PosicionTab", 0);
            startActivity(intent);
        }
        else{
            onBackPressed();
        }
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
                irAtras();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            irAtras();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void dialogoCompleta(){
        DialogoCompleta dialogoPersonalizado = new DialogoCompleta();
        dialogoPersonalizado.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogTransparente);
        dialogoPersonalizado.setCancelable(false);
        dialogoPersonalizado.show(getSupportFragmentManager(), "personalizado");
        android.app.Fragment frag = getFragmentManager().findFragmentByTag("personalizado");

        if (frag != null) {
            getFragmentManager().beginTransaction().remove(frag).commit();
        }
    }

}

