package com.example.admin.proyectoandroid;

/**
 * Created by sebyc on 21/01/2016.
 */
public class clsUsuario {

    private int id;
    private int fuerza;
    private int destreza;
    private int inteligencia;
    private String nombre;
    private int exp;
    private int nivel;
    private int nmisiones;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getNmisiones() {
        return nmisiones;
    }

    public void setNmisiones(int nmisiones) {
        this.nmisiones = nmisiones;
    }

    public clsUsuario()
    {

    }

    public clsUsuario(int id,int fuerza,int destreza,int inteligencia,int nmisiones,int exp,int nivel,String nombre)
    {
        this.id=id;
        this.fuerza=fuerza;
        this.destreza=destreza;
        this.inteligencia=inteligencia;
        this.nmisiones=nmisiones;
        this.exp=exp;
        this.nombre=nombre;
        this.nivel=nivel;
    }
}
