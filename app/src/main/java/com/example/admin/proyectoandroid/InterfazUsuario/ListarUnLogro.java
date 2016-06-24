package com.example.admin.proyectoandroid.InterfazUsuario;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    private ViewGroup linearLayoutDescripcion;
    private ImageView imageViewExpandir, imageViewLogros;

    private static final int DURATION = 250;


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
            imagenes[i] = getResources().getIdentifier(logros.get(i).getNombreimagen().toString()+"_grande" , "drawable",  getPackageName());
        }

        /*INDICAR TITULO Y SUBTITULO */
        if (ab != null) {
            ab.setTitle(titulos[position]);
        }


        linearLayoutDescripcion = (ViewGroup) findViewById(R.id.linearLayoutDescripcionLogros);
        imageViewExpandir = (ImageView) findViewById(R.id.imageViewExpandir);
        imageViewLogros = (ImageView) findViewById(R.id.imageViewLogros);
        tvDescripcion = (TextView) findViewById(R.id.textViewInfo);

        Toolbar toolbarCard = (Toolbar) findViewById(R.id.toolbarCard);
        toolbarCard.setTitle(titulos[position]);
        toolbarCard.setSubtitle("Estado: "+estados[position]);
        imageViewLogros.setImageResource(imagenes[position]);
        tvDescripcion.setText(descripciones[position]);


    }

    public void toggleDetails(View view) {
        if (linearLayoutDescripcion.getVisibility() == View.GONE) {
            ExpandAndCollapseViewUtil.expand(linearLayoutDescripcion, DURATION);
            imageViewExpandir.setImageResource(R.mipmap.more);
            rotate(-180.0f);
        } else {
            ExpandAndCollapseViewUtil.collapse(linearLayoutDescripcion, DURATION);
            imageViewExpandir.setImageResource(R.mipmap.less);
            rotate(180.0f);
        }
    }

    private void rotate(float angle) {
        Animation animation = new RotateAnimation(0.0f, angle, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(DURATION);
        imageViewExpandir.startAnimation(animation);
    }


}





