package com.example.admin.proyectoandroid.InterfazUsuario.Servicios;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.clsMision;

import java.util.ArrayList;

public class LlenarDatosDiarios extends BroadcastReceiver{

    ArrayList<clsMision> misiones = new ArrayList<clsMision>();

    @Override
    public void onReceive(Context context, Intent intent) {

        misiones = ((AplicacionPrincipal)context.getApplicationContext()).getMisionesActivas();
        for(int i = 0; i < misiones.size(); i++){
            if(misiones.get(i).getProgresoActual() < misiones.get(i).getProgreso()){
                ((AplicacionPrincipal)context.getApplicationContext()).registrarMisionFallida();
            }
        }
        ((AplicacionPrincipal)context.getApplicationContext()).llenarMisionesDiarias();
        ((AplicacionPrincipal)context.getApplicationContext()).llenarPreguntasDiarias();
    }



}
