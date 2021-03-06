package com.example.admin.proyectoandroid.InterfazUsuario;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.proyectoandroid.InterfazUsuario.AlertDialogos.DialogoCorrecta;
import com.example.admin.proyectoandroid.InterfazUsuario.AlertDialogos.DialogoIncorrecta;
import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.R;
import com.example.admin.proyectoandroid.clsPregunta;

import java.util.ArrayList;


public class ListarUnaPregunta extends ActionBarActivity {

    private TextView tvDescripcion;
    private Button btnOpcionA, btnOpcionB, btnOpcionC;
    ArrayList<clsPregunta> preguntas = new ArrayList<clsPregunta>();
    String[] descripcion;
    String[] categoria;
    String[] opcionA;
    String[] opcionB;
    String[] opcionC;
    String[] respuesta;
    boolean primerClick;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_una_pregunta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPregunta);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        Bundle  extras = getIntent().getExtras();
        position = extras.getInt("position");

        primerClick = false;

        preguntas = ((AplicacionPrincipal)getApplication()).getPreguntasActivas();
        descripcion = new String[preguntas.size()];
        categoria = new String[preguntas.size()];
        opcionA = new String[preguntas.size()];
        opcionB = new String[preguntas.size()];
        opcionC =  new String[preguntas.size()];
        respuesta =  new String[preguntas.size()];

        for(int i = 0; i < preguntas.size(); i++)
        {
            descripcion[i] = preguntas.get(i).getDescripcion();
            categoria[i] = preguntas.get(i).getCategoria();
            opcionA[i] = preguntas.get(i).getOpA();
            opcionB[i] = preguntas.get(i).getOpB();
            opcionC[i] = preguntas.get(i).getOpC();
            respuesta[i] = preguntas.get(i).getResp();
        }

        /*INDICAR TITULO, SUBTITULO Y AGREGAR ICONO HOME ATRAS*/
        if (ab != null) {
            ab.setTitle("Categoria: " + categoria[position]);
            int totalEstadisticas = preguntas.get(position).getDestreza() + preguntas.get(position).getInteligencia() + preguntas.get(position).getFuerza();
            ab.setSubtitle("Total estadisticas extras +"+ totalEstadisticas);
            ab.setHomeAsUpIndicator(R.drawable.icono_atras_2);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        tvDescripcion = (TextView) findViewById(R.id.tv_contenido_ListarUnaPregunta);
        btnOpcionA = (Button) findViewById(R.id.boton_opcionA);
        btnOpcionB = (Button) findViewById(R.id.boton_opcionB);
        btnOpcionC = (Button) findViewById(R.id.boton_opcionC);

        tvDescripcion.setText(descripcion[position]);
        btnOpcionA.setText("A: " + opcionA[position]);
        btnOpcionB.setText("B: " + opcionB[position]);
        btnOpcionC.setText("C: " + opcionC[position]);

        btnOpcionA.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!primerClick) {
                            cambiarColorOpciones();
                            if (((AplicacionPrincipal) getApplication()).responderPregunta(preguntas.get(position), opcionA[position])) {
                                dialogoCorrecta();
                            } else {
                                dialogoIncorrecta();
                            }
                            primerClick = true;
                        }
                    }
                }

        );

        btnOpcionB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!primerClick) {
                            cambiarColorOpciones();
                            if (((AplicacionPrincipal) getApplication()).responderPregunta(preguntas.get(position), opcionB[position])) {
                                dialogoCorrecta();
                            } else {
                                dialogoIncorrecta();
                            }
                            primerClick = true;
                        }
                    }
                }

        );

        btnOpcionC.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!primerClick) {
                            cambiarColorOpciones();
                            if (((AplicacionPrincipal) getApplication()).responderPregunta(preguntas.get(position), opcionC[position])) {
                                dialogoCorrecta();
                            } else {
                                dialogoIncorrecta();
                            }
                            primerClick = true;
                        }
                    }
                }

        );

    }

    public void cambiarColorOpciones(){
        if (respuesta[position].compareTo(opcionA[position]) == 0) {
            btnOpcionA.setBackgroundResource(R.drawable.boton_correcto);
            btnOpcionB.setBackgroundResource(R.drawable.boton_incorrecto);
            btnOpcionC.setBackgroundResource(R.drawable.boton_incorrecto);
        } else if (respuesta[position].compareTo(opcionB[position]) == 0) {
            btnOpcionA.setBackgroundResource(R.drawable.boton_incorrecto);
            btnOpcionB.setBackgroundResource(R.drawable.boton_correcto);
            btnOpcionC.setBackgroundResource(R.drawable.boton_incorrecto);
        } else {
            btnOpcionA.setBackgroundResource(R.drawable.boton_incorrecto);
            btnOpcionB.setBackgroundResource(R.drawable.boton_incorrecto);
            btnOpcionC.setBackgroundResource(R.drawable.boton_correcto);
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
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void dialogoCorrecta(){
        DialogoCorrecta dialogoPersonalizado = new DialogoCorrecta();
        dialogoPersonalizado.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogTransparente);
        dialogoPersonalizado.setCancelable(false);
        dialogoPersonalizado.show(getSupportFragmentManager(), "personalizado");
        android.app.Fragment frag = getFragmentManager().findFragmentByTag("personalizado");

        if (frag != null) {
            getFragmentManager().beginTransaction().remove(frag).commit();
        }
    }

    public void dialogoIncorrecta(){
        DialogoIncorrecta dialogoPersonalizado = new DialogoIncorrecta();
        dialogoPersonalizado.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogTransparente);
        dialogoPersonalizado.setCancelable(false);
        dialogoPersonalizado.show(getSupportFragmentManager(), "personalizado");
        android.app.Fragment frag = getFragmentManager().findFragmentByTag("personalizado");

        if (frag != null) {
            getFragmentManager().beginTransaction().remove(frag).commit();
        }
    }


}

