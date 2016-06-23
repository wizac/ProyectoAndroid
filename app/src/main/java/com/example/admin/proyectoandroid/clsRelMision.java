package com.example.admin.proyectoandroid;

/**
 * Created by Pablo on 23/06/2016.
 */
public class clsRelMision {

    private int idmision;
    private int idusuario;
    private int progreso;

    public int getIdmision() {
        return idmision;
    }

    public void setIdmision(int idmision) {
        this.idmision = idmision;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public clsRelMision()
    {

    }
    public clsRelMision(int idmision,int idusuario,int progreso)
    {
        this.idmision=idmision;
        this.idusuario=idusuario;
        this.progreso=progreso;
    }
}
