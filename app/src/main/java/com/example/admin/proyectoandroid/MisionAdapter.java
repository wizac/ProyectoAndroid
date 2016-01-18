package com.example.admin.proyectoandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by sebyc on 17/01/2016.
 */
public class MisionAdapter {
    private static final String NAME="mision";
    private SQLiteDatabase sqlDB;

    public MisionAdapter (SQLiteDatabase sqlDB)
    {
        this.sqlDB=sqlDB;
    }
    private class Columns implements BaseColumns
    {

        public final static String ID="idmision";
        public final static String PROGRESO="progreso";
        public final static String TITULO="titulo";
        public final static String DESCRIPCION="descripcion";
        public final static String EXP="exp";

    }

    private final static String[] COLUMNS= {

            Columns.ID,Columns.PROGRESO,Columns.TITULO,Columns.DESCRIPCION,Columns.EXP
    };

    public final static String CR_TABLE="create table if not exists " +
            NAME +"("+Columns.ID +" integer primary key autoincrement,"
            +Columns.PROGRESO +" integer not null, "
            +Columns.TITULO +" text not null, "
            +Columns.DESCRIPCION+" text not null, "
            +Columns.EXP+" integer not null)";

    public boolean insert(int progreso,String titulo, String descripcion,int exp)
    {
        ContentValues Values=new ContentValues();
        Values.put(Columns.PROGRESO,progreso);
        Values.put(Columns.TITULO,titulo);
        Values.put(Columns.DESCRIPCION,descripcion);
        Values.put(Columns.EXP,exp);


        return sqlDB.insert(NAME,null,Values)>0;
    }

    public boolean delete(int id)
    {
        String whereClause="idmision=?";
        String[] whereArgs={String.valueOf(id)};
        return sqlDB.delete(NAME, whereClause, whereArgs)>0;
    }
    public Cursor getNombres()
    {
        String[] columns={Columns.TITULO};
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
