package com.example.admin.proyectoandroid.Servicios;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.admin.proyectoandroid.AplicacionPrincipal;

public class LlenarMisionesDiarias extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        ((AplicacionPrincipal)context.getApplicationContext()).llenarMisionesDiarias();
    }



}
