package com.example.admin.proyectoandroid;

/**
 * Created by sebyc on 23/06/2016.
 */
public class clsLogro {

    private int idlogro;
    private String nombre;
    private String descripcion;
    private String estado;
    private String nombreimagen;

    public clsLogro()
    {

    }

    public clsLogro(int i,String nom,String desc,String est,String nomb)
    {
        setId(i);
        setNombre(nom);
        setDescripcion(desc);
        setEstado(est);
        setNombreimagen(nomb);
    }

    public int getId() {
        return idlogro;
    }

    public void setId(int id) {
        this.idlogro = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreimagen() {
        return nombreimagen;
    }

    public void setNombreimagen(String nombreimagen) {
        this.nombreimagen = nombreimagen;
    }
}
