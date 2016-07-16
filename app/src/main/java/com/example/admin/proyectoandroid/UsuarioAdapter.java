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
        public final static String EMAIL="email";
        public final static String MISIONESCOMPLETAS="misionesCompletas";
        public final static String MISIONESFALLIDAS="misionesFallidas";
        public final static String PREGUNTASSUPERADAS="preguntasSuperadas";
        public final static String PREGUNTASINCORRECTAS="preguntasIncorrectas";
    }

    private final static String[] COLUMNS= {

            Columns.ID,Columns.NOMBRE,Columns.NIVEL,Columns.EXP,Columns.NMISIONES,Columns.FUERZA,Columns.DESTREZA,Columns.INTELIGENCIA,Columns.EMAIL,Columns.MISIONESCOMPLETAS,Columns.MISIONESFALLIDAS,Columns.PREGUNTASSUPERADAS,Columns.PREGUNTASINCORRECTAS
    };

    public final static String CR_TABLE="create table if not exists " +
            NAME +"("+Columns.ID +" integer primary key autoincrement,"
            +Columns.NOMBRE +" text not null, "
            +Columns.NIVEL +" integer not null, "
            +Columns.EXP+" integer not null, "
            +Columns.NMISIONES+" integer not null, "
            +Columns.FUERZA+" integer not null, "
            +Columns.DESTREZA+" integer not null, "
            +Columns.INTELIGENCIA+" integer not null, "
            +Columns.EMAIL+" text not null, "
            +Columns.MISIONESCOMPLETAS+" integer not null, "
            +Columns.MISIONESFALLIDAS+" integer not null, "
            +Columns.PREGUNTASSUPERADAS+" integer not null, "
            +Columns.PREGUNTASINCORRECTAS+" integer not null)";

    public boolean insert(String nombre,int nivel,int exp,int nmisiones,int fuerza,int destreza,int inteligencia,String email,int misionesCompletas,int misionesFallidas,int preguntasSuperadas,int preguntasIncorrectas)
    {
        ContentValues Values=new ContentValues();
        Values.put(Columns.NOMBRE,nombre);
        Values.put(Columns.NIVEL,nivel);
        Values.put(Columns.EXP,exp);
        Values.put(Columns.NMISIONES,nmisiones);
        Values.put(Columns.FUERZA,fuerza);
        Values.put(Columns.DESTREZA,destreza);
        Values.put(Columns.INTELIGENCIA,inteligencia);
        Values.put(Columns.EMAIL,email);
        Values.put(Columns.MISIONESCOMPLETAS,misionesCompletas);
        Values.put(Columns.MISIONESFALLIDAS,misionesFallidas);
        Values.put(Columns.PREGUNTASSUPERADAS,preguntasSuperadas);
        Values.put(Columns.PREGUNTASINCORRECTAS,preguntasIncorrectas);

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
        int id=0;
        int nivel=0;
        int str=0;
        int dex=0;
        int wis=0;

        Cursor var;
       var= sqlDB.query(NAME,COLUMNS,null,null,null,null,null);

        if (var.moveToFirst()) {
            //me muevo al usuario que solo deberia haber uno
                id= var.getInt(0);
                nivel = var.getInt(2);
                nivel=nivel+1;

                sqlDB.execSQL("update usuario set nivel="+nivel+",exp=0 where idusuario="+id);
    }


    }

    public void aumentarEstadisticas(String misionOpregunta, String superadoOfallido){
        int id=0;
        int mS=0;
        int mF=0;
        int pS=0;
        int pF=0;

        Cursor var;
        var= sqlDB.query(NAME,COLUMNS,null,null,null,null,null);

        if (var.moveToFirst()) {
            //me muevo al usuario que solo deberia haber uno
            id = var.getInt(0);
            mS = var.getInt(9);
            mF = var.getInt(10);
            pS = var.getInt(11);
            pF = var.getInt(12);

            if (misionOpregunta == "M") {
                if (superadoOfallido == "S") {
                    mS += 1;
                    sqlDB.execSQL("update usuario set misionesCompletas=" + mS + " where idusuario=" + id);
                }else {
                    mF += 1;
                    sqlDB.execSQL("update usuario set misionesFallidas=" + mF + " where idusuario=" + id);
                }
            }else{
                if (superadoOfallido == "S") {
                    pS += 1;
                    sqlDB.execSQL("update usuario set preguntasSuperadas=" + pS + " where idusuario=" + id);
                }else {
                    pF += 1;
                    sqlDB.execSQL("update usuario set misionesIncorrectas=" + pF + " where idusuario=" + id);
                }
            }
        }
    }

    public void subirExp(int exp)
    {
        int id=0;
        int xp=0;
        Cursor var;
        var= sqlDB.query(NAME,COLUMNS,null,null,null,null,null);

        if (var.moveToFirst()) {
            id= var.getInt(0);
            xp =xp+var.getInt(3);

            sqlDB.execSQL("update usuario set exp="+xp +"where idusuario=" + id);
        }
    }
}
