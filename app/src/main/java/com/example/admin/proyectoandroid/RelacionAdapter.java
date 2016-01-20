package com.example.admin.proyectoandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by sebyc on 17/01/2016.
 */
public class RelacionAdapter {

    private static final String NAME="relacion";
    private SQLiteDatabase sqlDB;

    public RelacionAdapter (SQLiteDatabase sqlDB)
    {
        this.sqlDB=sqlDB;
    }
    private class Columns implements BaseColumns
    {

        public final static String ID="idmision";
        public final static String NOMBRE="nombre";
        public final static String PROGRESO="progreso";


    }

    private final static String[] COLUMNS= {

            Columns.ID,Columns.NOMBRE,Columns.PROGRESO
    };

    public final static String CR_TABLE="create table if not exists " +
            NAME +"("+Columns.ID +" integer not null FOREIGN KEY REFERENCES mision(idmision) ,"
            +Columns.NOMBRE +" integer not null FOREIGN KEY REFERENCES usuario(idusuario), "
            +Columns.PROGRESO +" int not null FOREIGN KEY REFERENCES mision(progreso) ";


    public boolean insert(String idmision,String nombre, String progreso)
    {
        ContentValues Values=new ContentValues();
        Values.put(Columns.ID,idmision);
        Values.put(Columns.NOMBRE,nombre);
        Values.put(Columns.PROGRESO,progreso);



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
    public void aumentarprogreso(int progreso)
    {

    }

}
