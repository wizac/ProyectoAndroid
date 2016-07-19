package com.example.admin.proyectoandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by Pablo on 23/06/2016.
 */
public class RelPreguntaAdapter {

    private static final String NAME="relPregunta";
    private SQLiteDatabase sqlDB;

    public RelPreguntaAdapter (SQLiteDatabase sqlDB)
    {
        this.sqlDB=sqlDB;
    }
    private class Columns implements BaseColumns
    {

        public final static String ID="idpregunta";
        public final static String USUARIO="idusuario";

    }

    private final static String[] COLUMNS= {

            Columns.ID,Columns.USUARIO
    };

    public final static String CR_TABLE="create table if not exists " +
            NAME +"("+ Columns.ID + " integer not null , "
            +Columns.USUARIO +" integer not null , "
            +"FOREIGN KEY(idpregunta) REFERENCES pregunta(idpregunta), "
            +"FOREIGN KEY(idusuario) REFERENCES usuario(idusuario))";




    public boolean insert(int idmision,int idusuario)
    {
        ContentValues Values=new ContentValues();
        Values.put(Columns.ID,idmision);
        Values.put(Columns.USUARIO,idusuario);

        return sqlDB.insert(NAME,null,Values)>0;
    }

    public boolean delete(int id)
    {
        String whereClause="idpregunta=?";
        String[] whereArgs={String.valueOf(id)};
        return sqlDB.delete(NAME, whereClause, whereArgs)>0;
    }
    public Cursor getNombres()
    {
        String[] columns={Columns.ID};
        return sqlDB.query(NAME,columns,null,null,null,null,null);
    }

    public String getName()
    {
        return NAME;
    }
    public String[] getColumns()
    {
        return COLUMNS;
    }
    public boolean isEmpty()
    {
        return sqlDB.query(NAME,COLUMNS,null,null,null,null,null).getCount()==0;
    }
    public Cursor getDatos()
    {
        return sqlDB.query(NAME,COLUMNS,null,null,null,null,null);
    }


    /*public void aumentarProgreso(int idmision,int cant)
    {
        Cursor mis;
        int progreso=0;
        mis=sqlDB.rawQuery("select * from relPregunta",null);
        mis.moveToFirst();
        progreso=progreso+mis.getInt(2);
        sqlDB.execSQL("update relPregunta set progreso="+progreso+" where idpregunta="+idmision);

    }*/

    public ArrayList<clsPregunta> preguntasActuales()
    {
        Cursor mis;
        Cursor dos;
        ArrayList<clsPregunta> asd=new ArrayList<>();


        mis=sqlDB.rawQuery("select * from relPregunta",null);

        if(mis.moveToFirst()) {

            do {
                clsPregunta x = new clsPregunta();
                x.setId(mis.getInt(0));

                dos = sqlDB.rawQuery("select * from pregunta where idpregunta=" + mis.getInt(0), null);
                dos.moveToFirst();

                x.setDescripcion(dos.getString(1));
                x.setOpA(dos.getString(2));
                x.setOpB(dos.getString(3));
                x.setOpC(dos.getString(4));
                x.setResp(dos.getString(5));
                x.setCategoria(dos.getString(6));
                x.setFuerza(dos.getInt(7));
                x.setDestreza(dos.getInt(8));
                x.setInteligencia(dos.getInt(9));
                x.setEstado(dos.getString(10));

                asd.add(x);
            }while (mis.moveToNext());
        }

        return asd;
    }
}
