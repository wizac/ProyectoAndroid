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
        int contador = 0;
        for(int i = 0; i < misiones.size(); i++){
            if(misiones.get(i).getProgresoActual() < misiones.get(i).getProgreso()){
                contador ++;
            }
        }
        if(contador > 0){
            ((AplicacionPrincipal)context.getApplicationContext()).registrarMisionFallida(contador);
        }
        ((AplicacionPrincipal)context.getApplicationContext()).llenarMisionesDiarias();
        ((AplicacionPrincipal)context.getApplicationContext()).llenarPreguntasDiarias();
    }



}
