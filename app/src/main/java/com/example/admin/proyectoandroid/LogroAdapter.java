package com.example.admin.proyectoandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by sebyc on 23/06/2016.
 */
public class LogroAdapter {
    private static final String NAME="logro";
    private SQLiteDatabase sqlDB;

    public LogroAdapter(SQLiteDatabase sqlDB)
    {
        this.sqlDB=sqlDB;
    }
    private class Columns implements BaseColumns
    {

        public final static String ID="idlogro";
        public final static String NOMBRE="nombre";
        public final static String DESCRIPCION="descripcion";
        public final static String ESTADO="estado";
        public final static String NOMBREIMAGEN="nombreimagen";

    }
    private final static String[] COLUMNS= {

            Columns.ID,Columns.NOMBRE,Columns.DESCRIPCION,Columns.ESTADO,Columns.NOMBREIMAGEN
    };

    public final static String CR_TABLE="create table if not exists " +
            NAME +"("+Columns.ID +" integer primary key autoincrement, "
            +Columns.NOMBRE +" text not null, "
            +Columns.DESCRIPCION +" text not null, "
            +Columns.ESTADO+" text not null, "
            +Columns.NOMBREIMAGEN+" text not null)";


    public boolean insert(int progreso,String titulo, String descripcion,int exp,String tipo)
    {
        ContentValues Values=new ContentValues();
        Values.put(Columns.NOMBRE,progreso);
        Values.put(Columns.DESCRIPCION,descripcion);
        Values.put(Columns.ESTADO,exp);
        Values.put(Columns.NOMBREIMAGEN,tipo);

        return sqlDB.insert(NAME,null,Values)>0;
    }

    public boolean delete(int id)
    {
        String whereClause="idlogro=?";
        String[] whereArgs={String.valueOf(id)};
        return sqlDB.delete(NAME, whereClause, whereArgs)>0;
    }
    public Cursor getNombres()
    {
        String[] columns={Columns.NOMBRE};
        return sqlDB.query(NAME,columns,null,null,null,null,null);
    }
    public Cursor getIdlogro()
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

    public void completarLogro(int id)
    {

            sqlDB.execSQL("update logro set estado='completo' where idlogro="+id);

    }

}
