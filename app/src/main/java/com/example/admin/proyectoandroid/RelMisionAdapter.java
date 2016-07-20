package com.example.admin.proyectoandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Pablo on 23/06/2016.
 */
public class RelMisionAdapter {

    private static final String NAME="relMision";
    private SQLiteDatabase sqlDB;

    public RelMisionAdapter (SQLiteDatabase sqlDB)
    {
        this.sqlDB=sqlDB;
    }
    private class Columns implements BaseColumns
    {

        public final static String ID="idmision";
        public final static String USUARIO="idusuario";
        public final static String PROGRESO="progreso";


    }

    private final static String[] COLUMNS= {

            Columns.ID,Columns.USUARIO,Columns.PROGRESO
    };

    public final static String CR_TABLE="create table if not exists " +
            NAME +"("+ Columns.ID + " integer not null , "
            +Columns.USUARIO +" integer not null , "
            +Columns.PROGRESO +" integer not null ,"
            +"FOREIGN KEY(idmision) REFERENCES mision(idmision), "
            +"FOREIGN KEY(idusuario) REFERENCES usuario(idusuario))";




    public boolean insert(int idmision,int idusuario, int progreso)
    {
        ContentValues Values=new ContentValues();
        Values.put(Columns.ID,idmision);
        Values.put(Columns.USUARIO,idusuario);
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
        mis=sqlDB.rawQuery("select * from relMision where idmision="+idmision,null);
        mis.moveToFirst();
        progreso=cant+mis.getInt(2);
        //Log.d("pregunta", "" + idmision + "---" + cant + "---" + progreso);
        sqlDB.execSQL("update relMision set progreso=" + progreso + " where idmision=" + idmision);

    }

    public ArrayList<clsMision> misionesActuales() {
        Cursor mis;
        Cursor dos;
        ArrayList<clsMision> asd = new ArrayList<>();


        mis = sqlDB.rawQuery("select * from relMision", null);

        if (mis.moveToFirst()){

            do {
                clsMision x = new clsMision();
                x.setId(mis.getInt(0));
                x.setProgresoActual(mis.getInt(2));

                dos = sqlDB.rawQuery("select * from mision where idmision=" + mis.getInt(0), null);
                dos.moveToFirst();

                x.setProgreso(dos.getInt(1));
                x.setTitulo(dos.getString(2));
                x.setDescripcion(dos.getString(3));
                x.setExp(dos.getInt(4));
                x.setTipo(dos.getString(5));
                x.setDificultad(dos.getString(6));

                dos.close();
                //Log.d("pregunta", "" + x.getId());
                asd.add(x);
            }while (mis.moveToNext());
        }

        mis.close();


        return asd;
    }
}
