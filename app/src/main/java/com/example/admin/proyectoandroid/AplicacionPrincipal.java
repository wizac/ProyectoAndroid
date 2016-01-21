package com.example.admin.proyectoandroid;

import android.app.Application;

/**
 * Created by Pablo on 21/01/2016.
 */
public class AplicacionPrincipal extends Application {

    DBAdapter dbAdapter;

    @Override
    public void onCreate()
    {
        dbAdapter = new DBAdapter(getApplicationContext());
        dbAdapter.open();

        super.onCreate();
    }

    @Override
    public void onTerminate()
    {
        dbAdapter.close();

        super.onTerminate();
    }

    public clsMision aumentarProgreso(int idMision)
    {
        return new clsMision();
    }

    public void subirNivel(int fuerza, int destreza, int inteligencia)
    {

    }

    public clsMision[] getMisionesDelDia()
    {
        clsMision[] misiones = new clsMision[4];

        for (int i = 0; i < 4; i++) {
            misiones[i] = dbAdapter.randomMision();
        }

        return misiones;
    }

    public clsPregunta getPreguntaDiaria()
    {
        return dbAdapter.randomPregunta();
    }


}
