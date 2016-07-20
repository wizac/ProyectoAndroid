package com.example.admin.proyectoandroid.InterfazUsuario.AlertDialogos;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.proyectoandroid.InterfazUsuario.FragmentCuenta;
import com.example.admin.proyectoandroid.InterfazUsuario.FragmentInicio;
import com.example.admin.proyectoandroid.InterfazUsuario.MainActivity;
import com.example.admin.proyectoandroid.R;

public class DialogoCompleta extends DialogFragment  {

    MediaPlayer mp = new MediaPlayer();

    public DialogoCompleta() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogo_completa, container);

        reproducirMisionCompleta();

        Button btnCompleta = (Button) view.findViewById(R.id.aceptar_completa);

        btnCompleta.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("ItemMenu", 0);
                        intent.putExtra("PosicionTab", 0);
                        startActivity(intent);
                        if (mp != null){
                            mp.stop();
                            if (mp.isPlaying()){
                                mp.stop();
                                mp.release();
                            }
                        }
                        dismiss();
                    }
                }
        );

        getDialog().getWindow().setGravity(Gravity.TOP);

        return view;
    }

    public void reproducirMisionCompleta(){
        mp.reset();
        mp.release();
        mp= MediaPlayer.create(getActivity(), R.raw.mision_completa);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setVolume(70,70);
        mp.seekTo(0);
        mp.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mp != null){
            mp.stop();
            if (mp.isPlaying()){
                mp.stop();
                mp.release();
            }
        }
    }

}

