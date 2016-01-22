package com.example.admin.proyectoandroid;

/**
 * Created by sebyc on 21/01/2016.
 */
public class clsRelacion {

    private int idmision;
    private String nombre;
    private int progreso;

    public int getIdmision() {
        return idmision;
    }

    public void setIdmision(int idmision) {
        this.idmision = idmision;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public clsRelacion()
    {

    }
    public clsRelacion(int idmision,String nombre,int progreso)
    {
        this.idmision=idmision;
        this.nombre=nombre;
        this.progreso=progreso;
    }
}
