package com.example.admin.proyectoandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.Random;

/**
 * Created by sebyc on 17/01/2016.
 */
public class PreguntasAdapter {


    private static final String NAME="pregunta";
    private SQLiteDatabase sqlDB;

    public PreguntasAdapter (SQLiteDatabase sqlDB)
    {
        this.sqlDB=sqlDB;
    }
    private class Columns implements BaseColumns
    {

        public final static String ID="idpregunta";
        public final static String DESCRIPCION="descripcion";
        public final static String OPCIONA="opciona";
        public final static String OPCIONB="opcionb";
        public final static String OPCIONC="opcionc";
        public final static String RESPUESTA="respuesta";
        public final static String CATEGORIA="categoria";
        public final static String FUERZA="fuerza";
        public final static String DESTREZA="destreza";
        public final static String INTELIGENCIA="inteligencia";

    }

    private final static String[] COLUMNS= {

            Columns.ID,Columns.DESCRIPCION,Columns.OPCIONA,Columns.OPCIONB,Columns.OPCIONC,Columns.RESPUESTA,Columns.CATEGORIA,Columns.FUERZA,Columns.DESTREZA,Columns.INTELIGENCIA
    };

    public final static String CR_TABLE="create table if not exists " +
            NAME +"("+Columns.ID +" integer primary key autoincrement,"
            +Columns.DESCRIPCION +" text not null, "
            +Columns.OPCIONA +" text not null, "
            +Columns.OPCIONB+" text not null, "
            +Columns.OPCIONC+" text not null, "
            +Columns.RESPUESTA+" text not null, "
            +Columns.CATEGORIA+" text not null, "
            +Columns.FUERZA+" integer not null, "
            +Columns.DESTREZA+" integer not null, "
            +Columns.INTELIGENCIA+" integer not null)";


    public boolean insert(String desc,String opciona, String opcionb,String opcionc,String respuesta,String categoria,int fuerza,int destreza,int inteligencia)
    {
        ContentValues Values=new ContentValues();

        Values.put(Columns.DESCRIPCION,desc);
        Values.put(Columns.OPCIONA,opciona);
        Values.put(Columns.OPCIONB,opcionb);
        Values.put(Columns.OPCIONC,opcionc);
        Values.put(Columns.RESPUESTA,respuesta);
        Values.put(Columns.CATEGORIA,categoria);
        Values.put(Columns.FUERZA,fuerza);
        Values.put(Columns.DESTREZA,destreza);
        Values.put(Columns.INTELIGENCIA,inteligencia);

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
        String[] columns={Columns.DESCRIPCION};
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

    public clsPregunta preguntaRandom ()
    {
        //cantidad calcula la cantidad de misiones , lo asigno a un cursor y despues
        // salto a la posicion que le diga el random para elegir una mision

        Cursor cantidad;
        Cursor mis;

        int cant;
        cantidad= sqlDB.rawQuery("Select count(*) from pregunta",null);

        cantidad.moveToFirst();
        cant=cantidad.getInt(0);

        int min = 1;
        int max = cant;

        Random r = new Random();
        int rand = r.nextInt(max - min + 1);

        mis=sqlDB.rawQuery("select * from pregunta",null);

        mis.moveToPosition(rand);

        clsPregunta c =new clsPregunta(mis.getInt(0),mis.getString(1),mis.getString(2),mis.getString(3),mis.getString(4),mis.getString(5),mis.getString(6),mis.getInt(7),mis.getInt(8),mis.getInt(9));

        return c;
    }

    public Cursor getIdPregunta()
    {
        String[] columns={Columns.ID};
        return sqlDB.query(NAME,columns,null,null,null,null,null);
    }


}
