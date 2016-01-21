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

    public void subirNivel()
    {

    }

    public clsMision[] getMisionesDelDia()
    {
        return new clsMision[1];
    }

    public clsPregunta getPreguntaDiaria()
    {
        return new clsPregunta();
    }


}
