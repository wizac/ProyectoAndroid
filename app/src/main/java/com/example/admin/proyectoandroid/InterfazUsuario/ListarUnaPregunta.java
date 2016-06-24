package com.example.admin.proyectoandroid.InterfazUsuario;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.R;
import com.example.admin.proyectoandroid.clsPregunta;

import java.util.ArrayList;


public class ListarUnaPregunta extends ActionBarActivity {
    private TextView tvDescripcion;
    private Button btnOpcionA, btnOpcionB, btnOpcionC, btnOpcionD;
    ArrayList<clsPregunta> preguntas = new ArrayList<clsPregunta>();
    String[] descripcion;
    String[] categoria;
    String[] opcionA;
    String[] opcionB;
    String[] opcionC;
    String[] opcionD;
    String[] respuesta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_una_pregunta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPregunta);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        Bundle  extras = getIntent().getExtras();
        int idPregunta= extras.getInt("idPregunta");
        final int position = extras.getInt("position");

        preguntas = ((AplicacionPrincipal)getApplication()).getPreguntasActivas();
        descripcion = new String[preguntas.size()];
        categoria = new String[preguntas.size()];
        opcionA = new String[preguntas.size()];
        opcionB = new String[preguntas.size()];
        opcionC =  new String[preguntas.size()];
        opcionD =  new String[preguntas.size()];
        respuesta =  new String[preguntas.size()];

        for(int i = 0; i < preguntas.size(); i++)
        {
            descripcion[i] = preguntas.get(i).getDescripcion();
            categoria[i] = preguntas.get(i).getCategoria();
            opcionA[i] = preguntas.get(i).getOpA();
            opcionB[i] = preguntas.get(i).getOpB();
            opcionC[i] = preguntas.get(i).getOpC();
            opcionD[i] = preguntas.get(i).getResp();
            respuesta[i] = preguntas.get(i).getResp();

        }

        /*INDICAR TITULO*/
        if (ab != null) {
            ab.setTitle("Categoria: " +categoria[position]);
        }

        tvDescripcion = (TextView) findViewById(R.id.tv_contenido_ListarUnaPregunta);
        btnOpcionA = (Button) findViewById(R.id.boton_opcionA);
        btnOpcionB = (Button) findViewById(R.id.boton_opcionB);
        btnOpcionC = (Button) findViewById(R.id.boton_opcionC);
        btnOpcionD = (Button) findViewById(R.id.boton_opcionD);

        tvDescripcion.setText(descripcion[position]);
        btnOpcionA.setText(opcionA[position]);
        btnOpcionB.setText(opcionB[position]);
        btnOpcionC.setText(opcionC[position]);
        btnOpcionD.setText(opcionD[position]);

    }


}

