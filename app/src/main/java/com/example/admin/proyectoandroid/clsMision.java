package com.example.admin.proyectoandroid;

/**
 * Created by sebyc on 20/01/2016.
 */
public class clsMision {

    private int id;
    private int progreso;
    private String titulo;
    private String descripcion;
    private int exp;
    private int progresoActual;
    private String tipo;
    private String dificultad;

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public clsMision()
    {

    }

    public clsMision(int id ,int exp,int progreso,String titulo,String descripcion,int progresoActual,String tipo,String dificultad)
    {
        this.id=id;
        this.exp=exp;
        this.descripcion=descripcion;
        this.progreso=progreso;
        this.titulo=titulo;
        this.progresoActual=progresoActual;
        this.tipo=tipo;
        this.dificultad=dificultad;
    }

    public int getProgresoActual()
    {
        return progresoActual;
    }

    public void setProgresoActual(int progresoActual)
    {
        this.progresoActual = progresoActual;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
