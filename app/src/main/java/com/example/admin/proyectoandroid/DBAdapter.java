package com.example.admin.proyectoandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sebyc on 17/01/2016.
 */
public class DBAdapter {
    private DBHelper dbHelper;
    private SQLiteDatabase sqlDB;
    private final static int DB_VERSION=1;
    private final static String DB_NAME="QuestLog";

    private MisionAdapter misionA;
    private PreguntasAdapter preguntasA;
    private RelacionAdapter relacionA;
    private UsuarioAdapter usuarioA;

    public DBAdapter(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void open()
    {
        sqlDB=dbHelper.getWritableDatabase();
        misionA=new MisionAdapter(sqlDB);
        preguntasA = new PreguntasAdapter(sqlDB);
        relacionA=new RelacionAdapter(sqlDB);
        usuarioA=new UsuarioAdapter(sqlDB);
    }
    public void close()
    {
        sqlDB.close();

    }

    public boolean usuarioIsEmpty()
    {
        return usuarioA.isEmpty();
    }


    public boolean misionInsert(int progreso,String titulo,String descripcion,int exp)
    {

        return misionA.insert(progreso, titulo, descripcion, exp);
    }

    public boolean preguntaInsert(String descripcion,String opa,String opb,String opc,String resp)
    {

       return preguntasA.insert(descripcion,opa,opb,opc,resp);
    }

    public boolean relacionInsert(String idmision,String nombre,int progreso)
    {

        return relacionA.insert(idmision, nombre, progreso);
    }
    public boolean usuarioInsert(String nombre,int nivel,int exp,int nmisiones,int fuerza,int destreza,int inteligencia)
    {

        return usuarioA.insert(nombre, nivel, exp, nmisiones, fuerza, destreza, inteligencia);
    }



    //usuarios

    public Cursor getNombreUsuario()
    {
        return usuarioA.getNombres();
    }
    public Cursor getDatosUsuario()
    {
        return usuarioA.getDatos();
    }

    public boolean borrarUsuario(int id)
    {
        return usuarioA.delete(id);
    }



    //misiones
    public Cursor getMisionesTitulo()
    {
        return misionA.getNombres();
    }
    public Cursor getDatosMision()
    {
        return misionA.getDatos();
    }

    public boolean borrarMision(int id)
    {
        return misionA.delete(id);
    }
    public Cursor getIdmision()
    {
        return misionA.getIdmision();
    }
    public clsMision randomMision()
    {
        return  misionA.misionRandom();
    }



    //preguntas
    public Cursor getPreguntaDescripcion()
        {
        return preguntasA.getNombres();
    }
    public Cursor getDatospregunta()
    {
        return preguntasA.getDatos();
    }

    public boolean borrarpregunta(int id)
    {
        return preguntasA.delete(id);
    }
    public Cursor getIdPregunta()
    {
        return preguntasA.getIdPregunta();
    }

    public clsPregunta randomPregunta()
    {
        return  preguntasA.preguntaRandom();
    }

    //Relacion
    public Cursor getIdrelacionXmision()
    {
        return relacionA.getNombres();
    }
    public Cursor getDatosRelacion()
    {
        return relacionA.getDatos();
    }

    public boolean borrarRelacion(int id)
    {
        return relacionA.delete(id);
    }







    private class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context)
        {
              super(context,DB_NAME,null,DB_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(UsuarioAdapter.CR_TABLE);
            db.execSQL(MisionAdapter.CR_TABLE);
            db.execSQL(RelacionAdapter.CR_TABLE);
            db.execSQL(PreguntasAdapter.CR_TABLE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
        {
            db.execSQL("drop table if exists "+UsuarioAdapter.CR_TABLE);
            db.execSQL("drop table if exists "+MisionAdapter.CR_TABLE);
            db.execSQL("drop table if exists "+RelacionAdapter.CR_TABLE);
            db.execSQL("drop table if exists "+PreguntasAdapter.CR_TABLE);
            onCreate(db);

        }
    }

}
