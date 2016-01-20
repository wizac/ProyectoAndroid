package com.example.admin.proyectoandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by sebyc on 17/01/2016.
 */
public class UsuarioAdapter {
    private static final String NAME="usuario";
    private SQLiteDatabase sqlDB;

    public UsuarioAdapter (SQLiteDatabase sqlDB)
    {
        this.sqlDB=sqlDB;
    }
    private class Columns implements BaseColumns
    {

        public final static String ID="idusuario";
        public final static String NOMBRE="nombre";
        public final static String NIVEL="nivel";
        public final static String EXP="exp";
        public final static String NMISIONES="nmisiones";
        public final static String FUERZA="fuerza";
        public final static String DESTREZA="destreza";
        public final static String INTELIGENCIA="inteligencia";
    }

    private final static String[] COLUMNS= {

            Columns.ID,Columns.NOMBRE,Columns.NIVEL,Columns.EXP,Columns.NMISIONES,Columns.FUERZA,Columns.DESTREZA,Columns.INTELIGENCIA
    };

    public final static String CR_TABLE="create table if not exists " +
            NAME +"("+Columns.ID +" integer primary key autoincrement,"
            +Columns.NOMBRE +" text not null, "
            +Columns.NIVEL +" integer not null, "
            +Columns.EXP+" integer not null, "
            +Columns.NMISIONES+" integer not null, "
            +Columns.FUERZA+" integer not null, "
            +Columns.DESTREZA+" integer not null, "
            +Columns.INTELIGENCIA+" integer not null)";

    public boolean insert(String nombre,int nivel,int exp,int nmisiones,int fuerza,int destreza,int inteligencia)
    {
        ContentValues Values=new ContentValues();
        Values.put(Columns.NOMBRE,nombre);
        Values.put(Columns.NIVEL,nivel);
        Values.put(Columns.EXP,exp);
        Values.put(Columns.NMISIONES,nmisiones);
        Values.put(Columns.FUERZA,fuerza);
        Values.put(Columns.DESTREZA,destreza);
        Values.put(Columns.INTELIGENCIA,inteligencia);

        return sqlDB.insert(NAME,null,Values)>0;
    }

    public boolean delete(int id)
    {
        String whereClause="idusuario=?";
        String[] whereArgs={String.valueOf(id)};
        return sqlDB.delete(NAME, whereClause, whereArgs)>0;
    }
    public Cursor getNombres()
    {
        String[] columns={Columns.NOMBRE};
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

    public void subirlvl()
    {
        Cursor var;
       var= sqlDB.query(NAME,COLUMNS,null,null,null,null,null);

        if (var.moveToFirst()) {
            //me muevo al usuario que solo deberia haber uno
                String id= var.getString(0);
                int nivel = var.getInt(2);
                int exp = var.getInt(3);

        }
    }
}
