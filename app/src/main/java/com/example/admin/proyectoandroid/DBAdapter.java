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
    private RelMisionAdapter relMisionA;
    private RelPreguntaAdapter relPreguntaA;
    private UsuarioAdapter usuarioA;
    private LogroAdapter logroA;

    public DBAdapter(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void open()
    {
        sqlDB=dbHelper.getWritableDatabase();
        misionA=new MisionAdapter(sqlDB);
        preguntasA = new PreguntasAdapter(sqlDB);
        relMisionA=new RelMisionAdapter(sqlDB);
        relPreguntaA=new RelPreguntaAdapter(sqlDB);
        usuarioA=new UsuarioAdapter(sqlDB);
        logroA=new LogroAdapter(sqlDB);
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

        return misionA.insert(a.getProgreso(), a.getTitulo(), a.getDescripcion(), a.getExp(), a.getTipo(), a.getDificultad());
    }

    public boolean preguntaInsert(clsPregunta a)
    {

       return preguntasA.insert(a.getDescripcion(),a.getOpA(),a.getOpB(),a.getOpC(),a.getResp(),a.getCategoria(),a.getFuerza(),a.getDestreza(),a.getInteligencia(),a.getEstado());
    }

    public boolean relMisionInsert(int idmision,int idusuario)
    {

        return relMisionA.insert(idmision,idusuario, 0);
    }
    public boolean relPreguntaInsert(int idpregunta,int idusuario)
    {

        return relPreguntaA.insert(idpregunta,idusuario);
    }
    public boolean usuarioInsert(String nombre,String email)
    {
        usuarioA.registrarUsuario(nombre,email);

        return true;
    }

    //usuarios

    public clsUsuario getNombreUsuario()
    {
        Cursor x=usuarioA.getNombres();
        clsUsuario y= new clsUsuario();

        //y.setNombre(x.getString(1)); ******************* CORREGIR EL CURSOS ESTA VACIO **********************
        y.setNombre("pablo");

        return y;
    }
    public clsUsuario getDatosUsuario()
    {
        Cursor x=usuarioA.getDatos();
        clsUsuario y= new clsUsuario();
        x.moveToFirst();

        y.setId(x.getInt(0));
        y.setNombre(x.getString(1));
        y.setNivel(x.getInt(2));
        y.setExp(x.getInt(3));
        y.setNmisiones(x.getInt(4));
        y.setFuerza(x.getInt(5));
        y.setDestreza(x.getInt(6));
        y.setInteligencia(x.getInt(7));
        y.setEmail(x.getString(8));
        y.setMisionesCompletas(x.getInt(9));
        y.setMisionesFallidas(x.getInt(10));
        y.setPreguntasSuperadas(x.getInt(11));
        y.setPreguntasIncorrectas(x.getInt(12));

        return y;
    }

    public void aumentarEstadisticas(String misionOpregunta, String superadaOfallida)
    {
        usuarioA.aumentarEstadisticas(misionOpregunta,superadaOfallida);
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

    //logros
    public void cumplirLogro(int id)
    {
        logroA.completarLogro(id);
    }

    public ArrayList<clsLogro> getDatosLogro()
    {
        Cursor M=logroA.getDatos();

        ArrayList<clsLogro> listalog=new ArrayList<clsLogro>();

        while (M.moveToNext())
        {
            clsLogro x=new clsLogro();
            x.setId(M.getInt(0));
            x.setNombre(M.getString(1));
            x.setEstado(M.getString(2));
            x.setDescripcion(M.getString(3));
            x.setNombreimagen(M.getString(4));




            listalog.add(x);
        }

        return listalog;
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
    public clsRelMision getIdrelacionXmision()
    {

        relMisionA.getNombres();
        clsRelMision x=new clsRelMision();
        return x;

    }
    public ArrayList<clsRelMision> getDatosRelMision()
    {

        Cursor x=relMisionA.getDatos();
        ArrayList<clsRelMision> lista=new ArrayList<clsRelMision>();

        while (x.moveToNext())
        {
            clsRelMision p = new clsRelMision();
            p.setIdmision(x.getInt(0));
            p.setIdusuario(x.getInt(1));
            p.setProgreso(x.getInt(2));

            lista.add(p);
        }
        return lista;
    }

    public void aumentarProgreso(int idmision,int cantidad )
    {
        relMisionA.aumentarProgreso(idmision, cantidad);
    }

    public boolean borrarRelMision(int id)
    {
        return relMisionA.delete(id);
    }

    public boolean borrarRelPregunta(int id)
    {
        return relPreguntaA.delete(id);
    }

    public void aumentarExp(int exp)
    {
        usuarioA.subirExp(exp);

    }

    public ArrayList<clsMision> misionesActivas()
    {
        ArrayList<clsMision> listamis=relMisionA.misionesActuales();

        return listamis;
    }

    public ArrayList<clsPregunta> preguntasActivas()
    {
        ArrayList<clsPregunta> listapre=relPreguntaA.preguntasActuales();

        return listapre;
    }

    public void subirlvl()
    {
        usuarioA.subirlvl();
    }

    public void completarMision(String attr)
    {
        misionA.completarMision(attr);
    }

    public void completarPregunta(clsPregunta pr)
    {
        preguntasA.completarPregunta(pr);
    }

    public clsMision buscarMisionPorId(int id)
    {
        clsMision asd=new clsMision();
        asd=misionA.buscarMisionPorId(id);

        return asd;
    }

    public void cambiarEstadoPregunta(int id,String estado){
        preguntasA.cambiarEstado(id,estado);
    }
    /*public void misionprueba()
    {
        misionA.misionprueba();
    }*/

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
            db.execSQL(LogroAdapter.CR_TABLE);
            db.execSQL(RelMisionAdapter.CR_TABLE);
            db.execSQL(PreguntasAdapter.CR_TABLE);
            db.execSQL(RelPreguntaAdapter.CR_TABLE);

            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(5,'Saluda a todos','Dile hola a 5 personas y consigue que ellos te devuelvan el saludo.',20,'I','Dificil')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(20,'A ejercitar','Ejercita haciendo 20 abdominales',35,'F','Normal')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(100,'Presiona el boton!','Preciona el boton de progreso 300 veces! Rapido rapido rapido!',50,'D','Facil')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(40,'Termina ese libro','Termina de leer ese libro que dejaste en el final, o busca un articulo interesante. Leer con frecuencia es bueno',25,'I','Despreciable')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(100,'Hora de la verdad','Confesa tus sentimientos a ese chico o chica que te gusta, es ahora o nunca!',100,'D','Fin del mundo')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(70,'Sal a correr','Trota un par de vueltas a la manzana, le hara bien a tu corazon. Y no olvides estirar!',50,'F','Suicidio')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(90,'5 a 5','Chocale los 5 a 5 personas distintas',35,'D','Insano')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(100,'Respondidos','Ve al modo preguntas y responde 10 preguntas correctas',50,'I','Normal')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(2,'Piedra, papel o tijeras','Juega piedra, papel o tijeras con alguien y ganale 2 veces',50,'D','Dificil')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(100,'Cara o cruz','Tira una modena y saca 5 caras, no deberia ser muy dificil...',20,'D','Facil')");

            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('pregunta1', 'opcionA', 'opcionB', 'opcionC', 'opcionA', 'historia',1,1,1,'P')");
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('pregunta2?', 'si', 'no', 'tal vez', 'no', 'deporte',2,2,2,'P')");
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('pregunta3?', 'A', 'B', 'C', 'B', 'arte',3,3,3,'P')");
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('pregunta4?', 'A', 'B', 'C', 'A', 'entretenimiento',1,0,1,'P')");
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('pregunta5?', 'A', 'B', 'C', 'C', 'arte',0,0,1,'P')");
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('pregunta6', 'A', 'B', 'C', 'A', 'deporte',4,0,0,'P')");

            db.execSQL("insert into logro (nombre,descripcion,estado,nombreimagen) values('El caballero','Llegar a tener mil puntos de fuerza','completo','el_caballero')");
            db.execSQL("insert into logro (nombre,descripcion,estado,nombreimagen) values('El cazador','Llegar a tener mil puntos de destreza','incompleto','el_cazador_u')");
            db.execSQL("insert into logro (nombre,descripcion,estado,nombreimagen) values('El mago','Llegar a tener mil puntos de inteligencia','incompleto','el_mago_u')");
            db.execSQL("insert into logro (nombre,descripcion,estado,nombreimagen) values('Nivel 10','LLega a nivel 10','completo','nivel_10')");
            db.execSQL("insert into logro (nombre,descripcion,estado,nombreimagen) values('Nivel 50','LLega a nivel 50','incompleto','nivel_50_u')");
            db.execSQL("insert into logro (nombre,descripcion,estado,nombreimagen) values('Nivel 100','LLega a nivel 100','incompleto','nivel_100_u')");

            db.execSQL("insert into usuario values(0, 'pablo', 1, 0, 0, 0, 0, 0, '', 0, 0, 0, 0)");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
        {
            db.execSQL("drop table if exists "+UsuarioAdapter.CR_TABLE);
            db.execSQL("drop table if exists "+LogroAdapter.CR_TABLE);
            db.execSQL("drop table if exists "+MisionAdapter.CR_TABLE);
            db.execSQL("drop table if exists "+RelMisionAdapter.CR_TABLE);
            db.execSQL("drop table if exists "+PreguntasAdapter.CR_TABLE);
            db.execSQL("drop table if exists "+RelPreguntaAdapter.CR_TABLE);
            onCreate(db);

        }
    }

}
