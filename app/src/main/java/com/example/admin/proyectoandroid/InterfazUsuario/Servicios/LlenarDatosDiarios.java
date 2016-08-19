package com.example.admin.proyectoandroid.InterfazUsuario.Servicios;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.admin.proyectoandroid.AplicacionPrincipal;
import com.example.admin.proyectoandroid.InterfazUsuario.MainActivity;
import com.example.admin.proyectoandroid.clsMision;
import com.example.admin.proyectoandroid.R;

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

        CrearNotificacion(context, "Mission Accomplished", "Tienes nuevas misiones y preguntas. Echa un vistazo", "Aviso");

        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags (Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.putExtra("close_activity",true);
        context.startActivity(i);
    }


    public void CrearNotificacion(Context context, String msg, String msgText, String msgAlert){

        PendingIntent notificIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(msg)
                .setTicker(msgAlert)
                .setContentText(msgText);

        mBuilder.setContentIntent(notificIntent);
        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }

}
