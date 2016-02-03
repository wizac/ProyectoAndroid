package com.example.admin.proyectoandroid;

import android.app.Application;

import java.util.ArrayList;

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

    public clsUsuario getUsuario()
    {
        return dbAdapter.getDatosUsuario();
    }

    public boolean aumentarProgreso(int idMision, int cantidad)
    {
        clsMision m = dbAdapter.buscarMisionPorId(idMision);

        if(m.getProgresoActual() + cantidad >= m.getProgreso())
        {
            dbAdapter.aumentarExp(m.getExp());
            if(dbAdapter.getDatosUsuario().getExp() >= (dbAdapter.getDatosUsuario().getNivel()*10+(dbAdapter.getDatosUsuario().getNivel()%10)*10))
            {
                return true;
            }
            return false;
        }
        else
        {
            dbAdapter.aumentarProgreso(idMision, cantidad);
            return false;
        }
    }

    public void subirNivel(int fuerza, int destreza, int inteligencia)
    {
        dbAdapter.subirlvl(fuerza, destreza, inteligencia);
    }

    public ArrayList<clsMision> llenarMisionesDiarias()
    {
        ArrayList<clsMision> misiones = new ArrayList<clsMision>();
        clsMision m = new clsMision();

        for (int i = 0; i < 5; i++) {
            m = dbAdapter.randomMision();
            misiones.add(m);
            dbAdapter.relacionInsert(m.getId(), dbAdapter.getNombreUsuario().getNombre());
        }

        return misiones;
    }

    public ArrayList<clsMision> getMisionesActivas()
    {
        return dbAdapter.misionesActivas();
    }

    public clsPregunta getPregunta()
    {
        return dbAdapter.randomPregunta();
    }

    public boolean responderPregunta (clsPregunta pregunta, String respuesta)
    {
        if (pregunta.getResp() == respuesta)
        {
            dbAdapter.aumentarExp(10);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void misionPrueba()
    {
        dbAdapter.misionprueba();
    }
}