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

        x.moveToFirst();

        y.setNombre(x.getString(1));

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
        Cursor m=logroA.getDatos();

        ArrayList<clsLogro> listalog=new ArrayList<clsLogro>();

        if(m.moveToFirst()) {
            do {
                clsLogro x = new clsLogro();
                x.setId(m.getInt(0));
                x.setNombre(m.getString(1));
                x.setEstado(m.getString(2));
                x.setDescripcion(m.getString(3));
                x.setNombreimagen(m.getString(4));

                listalog.add(x);
            }while (m.moveToNext());
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
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(20,'A ejercitar','Ejercita haciendo 20 abdominales',20,'F','Normal')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(300,'Presiona el boton!','Preciona el boton de progreso 300 veces! Rapido rapido rapido!',300,'D','Facil')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(1,'Termina ese libro','Termina de leer ese libro que dejaste en el final, o busca un articulo interesante. Leer con frecuencia es bueno',5,'I','Despreciable')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(1,'Hora de la verdad','Confesa tus sentimientos a ese chico o chica que te gusta, es ahora o nunca!',100,'D','Fin del mundo')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(1,'Sal a correr','Trota un par de vueltas a la manzana, le hara bien a tu corazon. Y no olvides estirar!',15,'F','Suicidio')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(5,'5 a 5','Chocale los 5 a 5 personas distintas',20,'D','Insano')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(10,'Respondidos','Ve al modo preguntas y responde 10 preguntas correctas',5,'I','Normal')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(2,'Piedra, papel o tijeras','Juega piedra, papel o tijeras con alguien y ganale 2 veces',20,'D','Dificil')");
            db.execSQL("insert into mision (progreso,titulo,descripcion,exp,tipo,dificultad) values(5,'Cara o cruz','Tira una modena y saca 5 caras, no deberia ser muy dificil...',10,'D','Facil')");

            //Entretenimiento
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('¿Quién es la mascota de SEGA?', 'Sonic', 'Mario', 'Pac Man', 'Sonic', 'entretenimiento',1,1,1,'P')");
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('¿Qué personaje del videojuego Mortal Kombat tiene poderes de hielo?', 'Scorpion', 'Sub-Zero', 'Motaro', 'Sub-Zero', 'entretenimiento',2,2,2,'C')");
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('¿Cuántos colores tiene un cubo de Rubik clásico?', '6', '4', '9', '6', 'entretenimiento',1,1,1,'P')");
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('¿Cuál es el nombre de Paul McCartney ?', 'James Paul McCartney', 'Sir Paul MacCartney', 'Paul McCartney Suer', 'James Paul McCartney', 'entretenimiento',1,1,1,'P')");

            //arte
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('¿Quién pintó el cuadro “El jardín de las delicias”?', 'Carvaggio', 'El Bosco', 'Arcimboldo', 'El Bosco', 'arte',3,3,3,'P')");
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('¿Quién escribió “El viejo y el mar?', 'Norman Mailer', 'Gabriel García Márquez', 'Ernest Hemingwa', 'Ernest Hemingway', 'arte',0,0,1,'I')");
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('¿Dónde nació Shakespeare?', 'Albacete', 'Surrey', 'Stratford-upon-Avon', 'Stratford-upon-Avon', 'arte',0,0,1,'I')");

            //deporte
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('¿Cuál es el estilo de natación más rápido?', 'Espalda', 'Mariposa', 'Crol', 'Crol', 'deporte',4,0,0,'P')");
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('¿Cuántos jugadores componen un equipo de rugby?.', '11', '15', '17', '15', 'deporte',4,0,0,'P')");
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('¿En qué país se inventó el voleibol?.', 'Gran Bretaña', 'Francia', 'Estados Unidos', 'Estados Unidos', 'deporte',4,0,0,'P')");
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('¿De qué color es el cero en el cilindro de la ruleta?.', 'Verde', 'Negro', 'Rojo', 'Verde', 'deporte',4,0,0,'P')");
            //historia
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('¿Cuál es la ciudad más antigua de América Latina?', 'Valparaíso', 'Caral', 'La Paz', 'Caral', 'historia',4,0,0,'P')");
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('¿Con qué emperador estuvo casada Cleopatra?', 'Ptolomeo XIV', 'Julio César', 'Todas son correctas', 'Todas son correctas', 'historia',4,0,0,'P')");
            db.execSQL("insert into pregunta(descripcion,opciona,opcionb,opcionc,respuesta,categoria,fuerza,destreza,inteligencia,estado) values('¿Qué país fue dirigido por Stalin?', 'Polonia', 'Alemania', 'Union Soviética', 'Union Soviética', 'historia',4,0,0,'P')");

            db.execSQL("insert into logro (nombre,descripcion,estado,nombreimagen) values('El caballero','Llegar a tener mil puntos de fuerza','incompleto','el_caballero')");
            db.execSQL("insert into logro (nombre,descripcion,estado,nombreimagen) values('El cazador','Llegar a tener mil puntos de destreza','incompleto','el_cazador')");
            db.execSQL("insert into logro (nombre,descripcion,estado,nombreimagen) values('El mago','Llegar a tener mil puntos de inteligencia','incompleto','el_mago')");
            db.execSQL("insert into logro (nombre,descripcion,estado,nombreimagen) values('Nivel 10','LLega a nivel 10','incompleto','nivel_10')");
            db.execSQL("insert into logro (nombre,descripcion,estado,nombreimagen) values('Nivel 50','LLega a nivel 50','incompleto','nivel_50')");
            db.execSQL("insert into logro (nombre,descripcion,estado,nombreimagen) values('Nivel 100','LLega a nivel 100','incompleto','nivel_100')");

            db.execSQL("insert into usuario values(0, '', 1, 0, 0, 0, 0, 0, '', 0, 0, 0, 0)");
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
