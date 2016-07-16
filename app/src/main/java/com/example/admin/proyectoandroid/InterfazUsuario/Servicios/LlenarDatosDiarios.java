package com.example.admin.proyectoandroid.InterfazUsuario.Servicios;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.admin.proyectoandroid.AplicacionPrincipal;

public class LlenarDatosDiarios extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        ((AplicacionPrincipal)context.getApplicationContext()).llenarMisionesDiarias();
        ((AplicacionPrincipal)context.getApplicationContext()).llenarPreguntasDiarias();
    }



}
