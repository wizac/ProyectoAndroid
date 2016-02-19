package com.example.admin.proyectoandroid;

/**
 * Created by sebyc on 20/01/2016.
 */
public class clsPregunta {

        private int id;
        private String descripcion;
        private String opA;
        private String opB;
        private String opC;
        private String resp;
        private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getOpA() {
        return opA;
    }

    public void setOpA(String opA) {
        this.opA = opA;
    }

    public String getOpB() {
        return opB;
    }

    public void setOpB(String opB) {
        this.opB = opB;
    }

    public String getOpC() {
        return opC;
    }

    public void setOpC(String opC) {
        this.opC = opC;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public clsPregunta()
    {

    }

    public clsPregunta(int id,String desc,String a,String b,String c,String resp,String categoria)
    {
        this.id=id;
        this.descripcion=desc;
        this.opA=a;
        this.opB=b;
        this.opC=c;
        this.resp=resp;
        this.categoria=categoria;
    }


}
