package com.example.admin.proyectoandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

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


    }

    private final static String[] COLUMNS= {

            Columns.ID,Columns.DESCRIPCION,Columns.OPCIONA,Columns.OPCIONB,Columns.OPCIONC,Columns.RESPUESTA
    };

    public final static String CR_TABLE="create table if not exists " +
            NAME +"("+Columns.ID +" integer primary key autoincrement,"
            +Columns.DESCRIPCION +" text not null, "
            +Columns.OPCIONA +" text not null, "
            +Columns.OPCIONB+" text not null, "
            +Columns.OPCIONC+" text not null, "
            +Columns.RESPUESTA+" text not null)";


    public boolean insert(String idpregunta,String opciona, String opcionb,String opcionc,String respuesta)
    {
        ContentValues Values=new ContentValues();
        Values.put(Columns.ID,idpregunta);
        Values.put(Columns.OPCIONA,opciona);
        Values.put(Columns.OPCIONB,opcionb);
        Values.put(Columns.OPCIONC,opcionc);
        Values.put(Columns.RESPUESTA,respuesta);



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
}
