package com.example.admin.proyectoandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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


    public boolean misionInsert(clsMision a )
    {

        return misionA.insert(a.getProgreso(), a.getTitulo(), a.getDescripcion(), a.getExp(),a.getTipo());
    }

    public boolean preguntaInsert(clsPregunta a)
    {

       return preguntasA.insert(a.getDescripcion(),a.getOpA(),a.getOpB(),a.getOpC(),a.getResp(),a.getCategoria());
    }

    public boolean relacionInsert(int idmision,String nombre)
    {

        return relacionA.insert(idmision,nombre, 0);
    }
    public boolean usuarioInsert(String nombre)
    {

        return usuarioA.insert(nombre, 0, 0, 0, 0, 0, 0);
    }



    //usuarios

    public clsUsuario getNombreUsuario()
    {
        Cursor x=usuarioA.getNombres();
        clsUsuario y= new clsUsuario();


        y.setNombre(x.getString(1));


        return y;
    }
    public clsUsuario getDatosUsuario()
    {
        Cursor x=usuarioA.getDatos();
        clsUsuario y= new clsUsuario();

        y.setId(x.getInt(0));
        y.setNombre(x.getString(1));
        y.setNivel(x.getInt(2));
        y.setExp(x.getInt(3));
        y.setNmisiones(x.getInt(4));
        y.setFuerza(x.getInt(5));
        y.setDestreza(x.getInt(6));
        y.setInteligencia(x.getInt(7));

        return y;
    }



    public boolean borrarUsuario(int id)
    {
        return usuarioA.delete(id);
    }



    //misiones
    public ArrayList<clsMision> getMisionesTitulo()
    {
        Cursor M=misionA.getNombres();

        ArrayList<clsMision> listamis=new ArrayList<clsMision>();

        while (M.moveToNext())
        {
            clsMision x=new clsMision();
            x.setId(M.getInt(0));
            x.setTitulo(M.getString(2));
            listamis.add(x);
        }

        return listamis;
    }
    public ArrayList<clsMision> getDatosMision()
    {
        Cursor M=misionA.getNombres();

        ArrayList<clsMision> listamis=new ArrayList<clsMision>();

        while (M.moveToNext())
        {
            clsMision x=new clsMision();
            x.setId(M.getInt(0));
            x.setProgreso(M.getInt(1));
            x.setTitulo(M.getString(2));
            x.setDescripcion(M.getString(3));
            x.setExp(M.getInt(4));
            x.setProgresoActual(M.getInt(5));



            listamis.add(x);
        }

        return listamis;
    }

    public boolean borrarMision(int id)
    {
        return misionA.delete(id);
    }

    public clsMision getIdmision()
    {
        misionA.getIdmision();
        clsMision x=new clsMision();
        return x;

    }
    public clsMision randomMision()
    {
        return  misionA.misionRandom();
    }





    //preguntas
    public clsPregunta getPreguntaDescripcion()
        {
            preguntasA.getNombres();
            clsPregunta x=new clsPregunta();
            return x;


        }
    public clsPregunta getDatospregunta()
    {
        preguntasA.getDatos();
        clsPregunta x=new clsPregunta();
        return x;
    }

    public boolean borrarpregunta(int id)
    {
        return preguntasA.delete(id);
    }
    public clsPregunta getIdPregunta()
    {
        preguntasA.getIdPregunta();
        clsPregunta x=new clsPregunta();
        return x;
    }

    public clsPregunta randomPregunta()
    {
        return  preguntasA.preguntaRandom();
    }

    //Relacion
    public clsRelacion getIdrelacionXmision()
    {

        relacionA.getNombres();
        clsRelacion x=new clsRelacion();
        return x;

    }
    public ArrayList<clsRelacion> getDatosRelacion()
    {

        Cursor x=relacionA.getDatos();
        ArrayList<clsRelacion> lista=new ArrayList<clsRelacion>();

        while (x.moveToNext())
        {
            clsRelacion p = new clsRelacion();
            p.setIdmision(x.getInt(0));
            p.setNombre(x.getString(1));
            p.setProgreso(x.getInt(2));

            lista.add(p);
        }
        return lista;
    }

    public void aumentarProgreso(int idmision,int cantidad )
    {
        relacionA.aumentarProgreso(idmision, cantidad);
    }

    public boolean borrarRelacion(int id)
    {
        return relacionA.delete(id);
    }


    public void aumentarExp(int exp)
    {
        usuarioA.subirExp(exp);

    }

    public ArrayList<clsMision> misionesActivas()
    {


        ArrayList<clsMision> listamis=relacionA.misionesActuales();


        return listamis;
    }

    public void subirlvl()
    {
        usuarioA.subirlvl();
    }

    public void completarMision(String attr)
    {
        misionA.completarMision(attr);
    }

 public clsMision buscarMisionPorId(int id)
 {
     clsMision asd=new clsMision();
     asd=misionA.buscarMisionPorId(id);

     return asd;
 }
    public void misionprueba()
    {
        misionA.misionprueba();
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

            db.rawQuery("insert into mision values(5,'Saluda a todos','Dile hola a 5 personas y consigue que ellos te devuelvan el saludo.',20,'I')", null);
            db.rawQuery("insert into mision values(20,'A ejercitar','Ejercita haciendo 20 abdominales',35,'F')", null);
            db.rawQuery("insert into mision values(300,'Presiona el boton!','Preciona el boton de progreso 300 veces! Rapido rapido rapido!',50,'D')", null);
            db.rawQuery("insert into mision values(1,'Termina ese libro','Termina de leer ese libro que dejaste en el final, o busca un articulo interesante. Leer con frecuencia es bueno',25,'I')", null);
            db.rawQuery("insert into mision values(1,'Hora de la verdad','Confesa tus sentimientos a ese chico o chica que te gusta, es ahora o nunca!',100,'D')", null);
            db.rawQuery("insert into mision values(2,'Sal a correr','Trota un par de vueltas a la manzana, le hara bien a tu corazon. Y no olvides estirar!',50,'F')", null);
            db.rawQuery("insert into mision values(5,'5 a 5','Chocale los 5 a 5 personas distintas',35,'D')", null);
            db.rawQuery("insert into mision values(10,'Respondidos','Ve al modo preguntas y responde 10 preguntas correctas',50,'I')", null);
            db.rawQuery("insert into mision values(2,'Piedra, papel o tijeras','Juega piedra, papel o tijeras con alguien y ganale 2 veces',50,'D')", null);
            db.rawQuery("insert into mision values(5,'Cara o cruz','Tira una modena y saca 5 caras, no deberia ser muy dificil...',20,'D')", null);

            db.rawQuery("insert into pregunta values('es una pregunta?', 'si', 'no', 'tal vez', 'no lo se', 'cine')", null);

            db.rawQuery("insert into usuario values('', 1, 0, 0, 0, 0, 0)", null);
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
