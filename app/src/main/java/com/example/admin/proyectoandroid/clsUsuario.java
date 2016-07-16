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
    private String email;
    private int misionesCompletas;
    private int misionesFallidas;
    private int preguntasSuperadas;
    private int preguntasIncorrectas;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMisionesCompletas() {
        return misionesCompletas;
    }

    public void setMisionesCompletas(int misionesCompletas) {
        this.misionesCompletas = misionesCompletas;
    }

    public int getMisionesFallidas() {
        return misionesFallidas;
    }

    public void setMisionesFallidas(int misionesFallidas) {
        this.misionesFallidas = misionesFallidas;
    }

    public int getPreguntasSuperadas() {
        return preguntasSuperadas;
    }

    public void setPreguntasSuperadas(int preguntasSuperadas) {
        this.preguntasSuperadas = preguntasSuperadas;
    }

    public int getPreguntasIncorrectas() {
        return preguntasIncorrectas;
    }

    public void setPreguntasIncorrectas(int preguntasIncorrectas) {
        this.preguntasIncorrectas = preguntasIncorrectas;
    }
    public clsUsuario()
    {

    }

    public clsUsuario(int id,int fuerza,int destreza,int inteligencia,int nmisiones,int exp,int nivel,String nombre,String email,int misionesCompletas,int misionesFallidas,int preguntasSuperadas,int preguntasIncorrectas)
    {
        this.id=id;
        this.fuerza=fuerza;
        this.destreza=destreza;
        this.inteligencia=inteligencia;
        this.nmisiones=nmisiones;
        this.exp=exp;
        this.nombre=nombre;
        this.nivel=nivel;
        this.email=email;
        this.misionesCompletas=misionesCompletas;
        this.misionesFallidas=misionesFallidas;
        this.preguntasSuperadas=preguntasSuperadas;
        this.preguntasIncorrectas=preguntasIncorrectas;
    }
}
