package com.example.admin.proyectoandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.Random;

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
        public final static String TIPO="tipo";

    }

    private final static String[] COLUMNS= {

            Columns.ID,Columns.PROGRESO,Columns.TITULO,Columns.DESCRIPCION,Columns.EXP
    };

    public final static String CR_TABLE="create table if not exists " +
            NAME +"("+Columns.ID +" integer primary key autoincrement,"
            +Columns.PROGRESO +" integer not null, "
            +Columns.TITULO +" text not null, "
            +Columns.DESCRIPCION+" text not null, "
            +Columns.EXP+" integer not null, "
            +Columns.TIPO+" text not null)";

    public boolean insert(int progreso,String titulo, String descripcion,int exp,String tipo)
    {
        ContentValues Values=new ContentValues();
        Values.put(Columns.PROGRESO,progreso);
        Values.put(Columns.TITULO,titulo);
        Values.put(Columns.DESCRIPCION,descripcion);
        Values.put(Columns.EXP,exp);
        Values.put(Columns.TIPO,tipo);


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
    public Cursor getIdmision()
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

    public clsMision misionRandom ()
    {
        //cantidad calcula la cantidad de misiones , lo asigno a un cursor y despues
        // salto a la posicion que le diga el random para elegir una mision

        Cursor cantidad;
        Cursor mis;

        int cant;
        cantidad= sqlDB.rawQuery("Select count(*) from mision",null);

        cantidad.moveToFirst();
        cant=cantidad.getInt(0);

        int min = 1;
        int max = cant;

        Random r = new Random();
        int rand = r.nextInt(max - min + 1) + min;

        mis=sqlDB.rawQuery("select * from mision where idmision = " + rand,null);

        //mis.moveToPosition(rand);
        mis.moveToFirst();

        clsMision c =new clsMision(mis.getInt(0),mis.getInt(4),mis.getInt(1),mis.getString(2),mis.getString(3),0,mis.getString(5));

        return c;
    }

    public clsMision buscarMisionPorId(int id)
    {
        clsMision asd = new clsMision();
        Cursor cur1;
        Cursor cur2;

        cur1=sqlDB.rawQuery("select * from mision where idmision="+id,null);
        cur2=sqlDB.rawQuery("select progreso from relacion where idmision="+id,null);
        if(cur1.moveToFirst())
        {
            asd.setExp(cur1.getInt(1));
            asd.setId(cur1.getInt(0));
            asd.setDescripcion(cur1.getString(2));
            asd.setProgreso(cur1.getInt(3));
            asd.setTitulo(cur1.getString(4));
            asd.setTipo(cur1.getString(5));

        }

        if (cur2.moveToFirst())
        {
            asd.setProgresoActual(cur2.getInt(0));
        }
        else
        {
            asd.setProgresoActual(0);
        }




        return asd;
    }

    public void misionprueba()
    {
        sqlDB.execSQL(CR_TABLE);
        sqlDB.execSQL("insert into mision values(1,'texto','mas texto',5)");
    }

    public void completarMision(String attr)
    {
        if(attr=="F")
            sqlDB.execSQL("update usuario set fuerza=fuerza+1");
        if(attr=="D")
            sqlDB.execSQL("update usuario set destreza=destreza+1");
        if(attr=="I")
            sqlDB.execSQL("update usuario set inteligencia=inteligencia+1");
    }
}
