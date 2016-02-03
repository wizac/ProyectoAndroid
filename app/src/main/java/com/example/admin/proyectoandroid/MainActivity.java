package com.example.admin.proyectoandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    public String[] slideshow;


    protected static Integer[] misImagenes = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //VIEWPAGER
        slideshow = getResources().getStringArray(R.array.misiones);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.pager);

        viewPagerAdapter.addFragment(Fragmentos.newInstance(0, "RETOS", misImagenes[0]));
        viewPagerAdapter.addFragment(Fragmentos.newInstance(1, "MISIONES", misImagenes[1]));
        viewPagerAdapter.addFragment(Fragmentos.newInstance(2, "PREGUNTAS", misImagenes[2]));

        viewPager.setAdapter(viewPagerAdapter);
        //FIN VIEWPAGER

    }


    /****************** VIEWPAGER *********************/
    public class ViewPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments; //acá voy a guardar los fragments

        //constructor
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<Fragment>();
        }

        @Override
        public Fragment getItem(int position) {
            //return PlaceholderFragment.newInstance(position + 1);
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            //return 3;
            return this.fragments.size();
        }

        public void addFragment(Fragment xfragment){
            this.fragments.add(xfragment);
        }
    }
    /****************** FIN VIEWPAGER *********************/

    /****************** FRAGMENTOS *********************/
    public static class Fragmentos extends Fragment {
        /**
         Agregue "color"
         */
        private static final String CURRENT_VIEWVAPER ="currentviewpager";
        private static final String NOMBRE_MODALIDAD = "modalidad";
        private static final String IMAGEVIEW = "image";

        private int currentViewPager;
        private String nombre_modalidad;
        private int image;

        private ImageView animationView;
        private TextView helpTextView;
        private AnimationDrawable savinAnimation;
        boolean ayudaMostrada = false;

        public static Fragmentos newInstance(int currentViewPager, String nombre_modalidad, int image) {

            Fragmentos fragment = new Fragmentos();   //instanciamos un nuevo fragment

            Bundle args = new Bundle();                                 //guardamos los parametros
            args.putInt(CURRENT_VIEWVAPER, currentViewPager);
            args.putString(NOMBRE_MODALIDAD, nombre_modalidad);
            args.putInt(IMAGEVIEW, image);
            fragment.setArguments(args);
            fragment.setRetainInstance(true);     //agrego para que no se pierda los valores de la instancia
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //cuando crea una instancia de tipo PlaceholderFragment
            //si lo enviamos parametros, guarda esos
            //si no le envio nada, toma el color gris y un número aleatroio
            if(getArguments() != null){
                this.currentViewPager = getArguments().getInt(CURRENT_VIEWVAPER);
                this.nombre_modalidad = getArguments().getString(NOMBRE_MODALIDAD);
                this.image = getArguments().getInt(IMAGEVIEW);
            }
        }

        public void tiempo_animacion()
        {
            new CountDownTimer(7000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    savinAnimation.start();
                    helpTextView.setVisibility(View.VISIBLE);
                }
                @Override
                public void onFinish() {
                    savinAnimation.stop();
                    animationView.setVisibility(View.INVISIBLE);
                    helpTextView.setVisibility(View.INVISIBLE);
                }
            }.start();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_misiones, container, false);

            TextView tv_misiones = (TextView) rootView.findViewById(R.id.tv_misiones);
            tv_misiones.setText(nombre_modalidad);

            ImageView frg_image = (ImageView) rootView.findViewById(R.id.frg_imageView);
            frg_image.setImageResource(image);

            //ANIMACION AYUDA
            if(nombre_modalidad == "RETOS" && !ayudaMostrada) {
                helpTextView = (TextView) rootView.findViewById(R.id.titulo_ayuda);
                animationView = (ImageView) rootView.findViewById(R.id.animacion);
                animationView.setBackgroundResource(R.drawable.animacion_desplazamiento);
                savinAnimation = (AnimationDrawable) animationView.getBackground();
                tiempo_animacion();
                ayudaMostrada = true;
            }
            //FIN ANIMACION AYUDA

            frg_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), ListarMisiones.class);
                    i.putExtra("currentViewPager", currentViewPager);
                    i.putExtra("nombreModalidad", nombre_modalidad);
                    startActivity(i);
                }
            });
            return rootView;
        }
    }
    /****************** FIN FRAGMENTOS *********************/
}
