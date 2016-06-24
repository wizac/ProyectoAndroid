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
            dbAdapter.aumentarProgreso(idMision, cantidad);
            
            if(dbAdapter.getDatosUsuario().getExp() >= (dbAdapter.getDatosUsuario().getNivel()*10+(dbAdapter.getDatosUsuario().getNivel()%10)*10))
            {
                dbAdapter.subirlvl();

                return true;
            }

            dbAdapter.completarMision(m.getTipo());
            dbAdapter.borrarRelMision(m.getId());

            return false;
        }
        else
        {
            dbAdapter.aumentarProgreso(idMision, cantidad);
            return false;
        }
    }

    public clsMision buscarMisionPorId(int idMision)
    {
        clsMision m = dbAdapter.buscarMisionPorId(idMision);

        return m;
    }

    /*public void subirNivel(int fuerza, int destreza, int inteligencia)
    {
        dbAdapter.subirlvl(fuerza, destreza, inteligencia);
    }*/

    public ArrayList<clsMision> llenarMisionesDiarias()
    {
        for (clsMision m : dbAdapter.misionesActivas())
        {
            dbAdapter.borrarRelMision(m.getId());
        }

        ArrayList<clsMision> misiones = new ArrayList<clsMision>();
        clsMision m;

        for (int i = 0; i < 5; i++)
        {
            m = dbAdapter.randomMision();

            for (clsMision mis: misiones) {

                if(m.getId()==mis.getId())
                {
                    do
                    {
                        m = dbAdapter.randomMision();
                    }
                    while(misiones.contains(m));
                }
            }

            misiones.add(m);
            dbAdapter.relMisionInsert(m.getId(), dbAdapter.getDatosUsuario().getId());
        }

        return misiones;
    }

    public ArrayList<clsPregunta> llenarPreguntasDiarias()
    {
        for (clsPregunta p : dbAdapter.preguntasActivas())
        {
            dbAdapter.borrarRelPregunta(p.getId());
        }

        ArrayList<clsPregunta> preguntas = new ArrayList<clsPregunta>();
        clsPregunta p;

        for (int i = 0; i < 5; i++)
        {
            do
            {
                p = dbAdapter.randomPregunta();
            }
            while(preguntas.contains(p));

            preguntas.add(p);
            dbAdapter.relPreguntaInsert(p.getId(), dbAdapter.getDatosUsuario().getId());
        }

        return preguntas;
    }

    public ArrayList<clsMision> getMisionesActivas()
    {
        return dbAdapter.misionesActivas();
    }

    public ArrayList<clsPregunta> getPreguntasActivas()
    {
        return dbAdapter.preguntasActivas();
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
            dbAdapter.completarPregunta(pregunta);
            dbAdapter.borrarRelPregunta(pregunta.getId());
            return true;
        }
        else
        {
            dbAdapter.borrarRelPregunta(pregunta.getId());
            return false;
        }
    }

    public ArrayList<clsLogro> getLogros()
    {
        return dbAdapter.getDatosLogro();
    }

    public void insertarUsuario(String nombre){
        dbAdapter.usuarioInsert(nombre);
    }

    /*public void misionPrueba()
    {
        dbAdapter.misionprueba();
    }*/
}
