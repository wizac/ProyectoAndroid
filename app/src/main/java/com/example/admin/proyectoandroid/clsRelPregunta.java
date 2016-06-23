package com.example.admin.proyectoandroid;

/**
 * Created by Pablo on 23/06/2016.
 */
public class clsRelPregunta {

    private int idpregunta;
    private int idusuario;

    public int getIdmision() {
        return idpregunta;
    }

    public void setIdmision(int idpregunta) {
        this.idpregunta = idpregunta;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public clsRelPregunta()
    {

    }
    public clsRelPregunta(int idmision,int idusuario)
    {
        this.idpregunta=idmision;
        this.idusuario=idusuario;
    }
}
