package com.example.admin.proyectoandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

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
            NAME +"("+ Columns.ID + " integer not null , "
            +Columns.NOMBRE +" integer not null , "
            +Columns.PROGRESO +" integer not null ,"
            +"FOREIGN KEY(idmision) REFERENCES mision(idmision), "
            +"FOREIGN KEY(nombre) REFERENCES usuario(nombre))";




    public boolean insert(int idmision,String nombre, int progreso)
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


    public void aumentarProgreso(int idmision,int cant)
    {
        Cursor mis;
        int progreso=0;
        mis=sqlDB.rawQuery("select * from relacion",null);
        progreso=progreso+mis.getInt(2);
        sqlDB.execSQL("update relacion set progreso="+progreso+" where idmision="+idmision);

    }

    public ArrayList<clsMision> misionesActuales()
    {
        Cursor mis;
        Cursor dos;
        ArrayList<clsMision> asd=new ArrayList<>();


        mis=sqlDB.rawQuery("select * from relacion",null);


        while (mis.moveToNext())
        {

            clsMision x=new clsMision();
            x.setId(mis.getInt(0));
            x.setProgresoActual(mis.getInt(2));

            dos=sqlDB.rawQuery("select * from mision where idmision="+mis.getInt(0),null);

            x.setProgreso(dos.getInt(1));
            x.setTitulo(dos.getString(2));
            x.setDescripcion(dos.getString(3));
            x.setExp(dos.getInt(4));




            asd.add(x);
        }

        return asd;
    }

}
