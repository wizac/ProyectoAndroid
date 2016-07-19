package com.example.admin.proyectoandroid;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

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

    public void cumplirLogros(){
        ArrayList<clsLogro> ll = dbAdapter.getDatosLogro();
        clsUsuario u = dbAdapter.getDatosUsuario();

        for(clsLogro l : ll){
            if(l.getNombre().equals("El caballero")){
                if(u.getFuerza() >= 1000){
                    dbAdapter.cumplirLogro(l.getId());
                }
            }else if(l.getNombre().equals("El cazador")){
                if(u.getDestreza() >= 1000){
                    dbAdapter.cumplirLogro(l.getId());
                }
            }else if(l.getNombre().equals("El mago")){
                if(u.getInteligencia() >= 1000){
                    dbAdapter.cumplirLogro(l.getId());
                }
            }else if(l.getNombre().equals("Nivel 10")){
                if(u.getNivel() >= 10){
                    dbAdapter.cumplirLogro(l.getId());
                }
            }else if(l.getNombre().equals("Nivel 50")){
                if(u.getInteligencia() >= 50){
                    dbAdapter.cumplirLogro(l.getId());
                }
            }else if(l.getNombre().equals("Nivel 100")){
                if(u.getInteligencia() >= 100){
                    dbAdapter.cumplirLogro(l.getId());
                }
            }
        }
    }

    public boolean aumentarProgreso(int idMision, int cantidad)
    {
        clsMision m = dbAdapter.buscarMisionPorId(idMision);

        if(m.getProgresoActual() + cantidad >= m.getProgreso())
        {
            dbAdapter.aumentarExp(m.getExp());
            dbAdapter.aumentarProgreso(idMision, cantidad);
            dbAdapter.aumentarEstadisticas("M", "S");
            
            if(dbAdapter.getDatosUsuario().getExp() >= (dbAdapter.getDatosUsuario().getNivel()*10+(dbAdapter.getDatosUsuario().getNivel()%10)*10))
            {
                dbAdapter.subirlvl();

                return true;
            }

            dbAdapter.completarMision(m.getTipo());
            //dbAdapter.borrarRelMision(m.getId());
            cumplirLogros();

            return false;
        }
        else
        {
            dbAdapter.aumentarProgreso(idMision, cantidad);
            cumplirLogros();
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

        boolean f = false;

        for (int i = 0; i < 5; i++){
            f = false;
            m = dbAdapter.randomMision();
            while(!f){
                f = true;
                m = dbAdapter.randomMision();
                for (clsMision mis: misiones) {
                    if(m.getId()==mis.getId())
                    {
                        f = false;
                    }
                }
            }
            //Log.d("pregunta", "" + m.getId());
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

        boolean f = false;

        for (int i = 0; i < 5; i++){
            f = false;
            p = dbAdapter.randomPregunta();
            while(!f){
                f = true;
                p = dbAdapter.randomPregunta();
                for (int j = 0; j < preguntas.size(); j++) {
                    //Log.d("pregunta", preguntas.get(j).getId() + "  --  " + p.getId());
                    if(p.getId()==preguntas.get(j).getId())
                    {
                        //Log.d("pregunta", "NO  -  " + p.getDescripcion() );
                        f = false;
                    }
                }
            }
            preguntas.add(p);
            dbAdapter.cambiarEstadoPregunta(p.getId(),"P");
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
        if (pregunta.getResp().equals(respuesta))
        {
            dbAdapter.aumentarExp(10);
            dbAdapter.completarPregunta(pregunta);
            //dbAdapter.borrarRelPregunta(pregunta.getId());
            dbAdapter.cambiarEstadoPregunta(pregunta.getId(),"C");
            dbAdapter.aumentarEstadisticas("P","S");
            cumplirLogros();
            return true;
        }
        else
        {
            //dbAdapter.borrarRelPregunta(pregunta.getId());
            dbAdapter.cambiarEstadoPregunta(pregunta.getId(),"I");
            dbAdapter.aumentarEstadisticas("P","F");
            cumplirLogros();
            return false;
        }
    }

    public void registrarMisionFallida(){
        dbAdapter.aumentarEstadisticas("M","F");
    }

    public ArrayList<clsLogro> getLogros()
    {
        return dbAdapter.getDatosLogro();
    }

    public void insertarUsuario(String nombre,String email){
        dbAdapter.usuarioInsert(nombre, email);
    }

    public int porcentajeProgreso(clsMision mision){
        int p = mision.getProgresoActual() * 100 / mision.getProgreso();
        if(p > 100){
            p = 100;
        }
        return p;
    }

    /*public void misionPrueba()
    {
        dbAdapter.misionprueba();
    }*/
}
