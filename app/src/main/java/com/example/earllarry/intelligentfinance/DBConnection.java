package com.example.earllarry.intelligentfinance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EarlLarry on 15-Feb-16.
 */
public class DBConnection extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "IntelligentFinance.db";

    private static final String USUARIO_TABLE_NAME = "Usuario";
    private static final String USUARIO_COLUMN_NAME = "nombre";

    private static final String INGRESO_TABLE_NAME = "Ingreso";
    private static final String INGRESO_COLUMN_ID = "id";
    private static final String INGRESO_COLUMN_CONCEPTO = "concepto";
    private static final String INGRESO_COLUMN_MONTO = "monto";
    private static final String INGRESO_COLUMN_TIPO = "tipo";
    private static final String INGRESO_COLUMN_AUTOMATIZAR = "automatizar";
    private static final String INGRESO_COLUMN_FECHA = "fecha";

    private static final String GASTO_TABLE_NAME = "Gasto";
    private static final String GASTO_COLUMN_ID = "id";
    private static final String GASTO_COLUMN_CONCEPTO = "concepto";
    private static final String GASTO_COLUMN_MONTO = "monto";
    private static final String GASTO_COLUMN_TIPO = "tipo";
    private static final String GASTO_COLUMN_AUTOMATIZAR = "automatizar";
    private static final String GASTO_COLUMN_FECHA = "fecha";

    private static final String TARJETA_TABLE_NAME = "Tarjeta";
    private static final String TARJETA_COLUMN_ID = "id";
    private static final String TARJETA_COLUMN_BANCO = "banco";
    private static final String TARJETA_COLUMN_MONTO = "monto";
    private static final String TARJETA_COLUMN_FOURDIGITS = "4digitos";
    private static final String TARJETA_COLUMN_INTERES = "interes";
    private static final String TARJETA_COLUMN_CORTE = "corte";
    private static final String TARJETA_COLUMN_VENCIMIENTO = "vencimiento";

    private static final String META_TABLE_NAME = "Meta";
    private static final String META_COLUMN_ID = "id";
    private static final String META_COLUMN_CONCEPTO = "concepto";
    private static final String META_COLUMN_MONTO = "monto";
    private static final String META_COLUMN_TIPO = "tipo";
    private static final String META_COLUMN_AUTOMATIZAR = "automatizar";
    private static final String META_COLUMN_FECHA = "fecha";

    public static final String[] ALL_COLUMNS_USUARIO = new String[] {
            USUARIO_COLUMN_NAME,
    };

    public static final String[] ALL_COLUMNS_INGRESO = new String[] {
            INGRESO_COLUMN_ID,
            INGRESO_COLUMN_MONTO,
            INGRESO_COLUMN_TIPO,
            INGRESO_COLUMN_CONCEPTO,
            INGRESO_COLUMN_AUTOMATIZAR,
            INGRESO_COLUMN_FECHA
    };

    public static final String[] ALL_COLUMNS_GASTO = new String[] {
            GASTO_COLUMN_ID,
            GASTO_COLUMN_MONTO,
            GASTO_COLUMN_TIPO,
            GASTO_COLUMN_CONCEPTO,
            GASTO_COLUMN_AUTOMATIZAR,
            GASTO_COLUMN_FECHA
    };

    public static final String[] ALL_COLUMNS_TARJETA = new String[] {
            TARJETA_COLUMN_ID,
            TARJETA_COLUMN_MONTO,
            TARJETA_COLUMN_FOURDIGITS,
            TARJETA_COLUMN_BANCO,
            TARJETA_COLUMN_CORTE,
            TARJETA_COLUMN_INTERES,
            TARJETA_COLUMN_VENCIMIENTO
    };

    public static final String[] ALL_COLUMNS_META = new String[] {
            META_COLUMN_ID,
            META_COLUMN_MONTO,
            META_COLUMN_TIPO,
            META_COLUMN_CONCEPTO,
            META_COLUMN_AUTOMATIZAR,
            META_COLUMN_FECHA
    };

    public static final String CREATE_USUARIO_TABLE =
            "CREATE TABLE " +
                    USUARIO_TABLE_NAME +
                    "( " +
                    USUARIO_COLUMN_NAME + " text, " +
                    ")";

    public static final String CREATE_INGRESO_TABLE =
            "CREATE TABLE " +
                    INGRESO_TABLE_NAME +
                    "( " +
                    INGRESO_COLUMN_ID + " integer primary key, " +
                    INGRESO_COLUMN_CONCEPTO + " text, " +
                    INGRESO_COLUMN_TIPO + " text " +
                    INGRESO_COLUMN_MONTO + " real, " +
                    INGRESO_COLUMN_AUTOMATIZAR + " integer " +
                    INGRESO_COLUMN_FECHA + " text " +
                    ")";

    public static final String CREATE_GASTO_TABLE =
            "CREATE TABLE " +
                    GASTO_TABLE_NAME +
                    "( " +
                    GASTO_COLUMN_ID + " integer primary key, " +
                    GASTO_COLUMN_CONCEPTO + " text, " +
                    GASTO_COLUMN_TIPO + " text " +
                    GASTO_COLUMN_MONTO + " real, " +
                    GASTO_COLUMN_AUTOMATIZAR + " integer " +
                    GASTO_COLUMN_FECHA + " text " +
                    ")";

    public static final String CREATE_META_TABLE =
            "CREATE TABLE " +
                    META_TABLE_NAME +
                    "( " +
                    META_COLUMN_ID + " integer primary key, " +
                    META_COLUMN_CONCEPTO + " text, " +
                    META_COLUMN_TIPO + " text " +
                    META_COLUMN_MONTO + " real, " +
                    META_COLUMN_AUTOMATIZAR + " integer " +
                    META_COLUMN_FECHA + " text " +
                    ")";

    public static final String CREATE_TARJETA_TABLE =
            "CREATE TABLE " +
                    TARJETA_TABLE_NAME +
                    "( " +
                    TARJETA_COLUMN_ID + " integer primary key, " +
                    TARJETA_COLUMN_BANCO + " text, " +
                    TARJETA_COLUMN_VENCIMIENTO + " text " +
                    TARJETA_COLUMN_MONTO + " real, " +
                    TARJETA_COLUMN_FOURDIGITS + " integer " +
                    TARJETA_COLUMN_CORTE + " text " +
                    TARJETA_COLUMN_INTERES + " real " +
                    ")";

    public DBConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_INGRESO_TABLE);
        db.execSQL(CREATE_GASTO_TABLE);
        db.execSQL(CREATE_META_TABLE);
        db.execSQL(CREATE_TARJETA_TABLE);
        db.execSQL(CREATE_USUARIO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + INGRESO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GASTO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + META_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TARJETA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USUARIO_TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=OFF");
        }
    }

    public void insertUsuario(String nombre) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(USUARIO_COLUMN_NAME, nombre);

        db.insert(USUARIO_TABLE_NAME, null, values);
    }

    public void insertIngreso(int id, double monto, String tipo, String concepto, boolean automatizar, String fecha) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(INGRESO_COLUMN_ID, id);
        values.put(INGRESO_COLUMN_MONTO, monto);
        values.put(INGRESO_COLUMN_TIPO, tipo);
        values.put(INGRESO_COLUMN_CONCEPTO, concepto);
        values.put(INGRESO_COLUMN_AUTOMATIZAR, automatizar);
        values.put(INGRESO_COLUMN_FECHA, fecha);

        db.insert(INGRESO_TABLE_NAME, null, values);
    }

    public void insertGasto(int id, double monto, String tipo, String concepto, boolean automatizar, String fecha) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(GASTO_COLUMN_ID, id);
        values.put(GASTO_COLUMN_MONTO, monto);
        values.put(GASTO_COLUMN_TIPO, tipo);
        values.put(GASTO_COLUMN_CONCEPTO, concepto);
        values.put(GASTO_COLUMN_AUTOMATIZAR, automatizar);
        values.put(GASTO_COLUMN_FECHA, fecha);

        db.insert(GASTO_TABLE_NAME, null, values);
    }

    public void insertMeta(int id, double monto, String tipo, String concepto, boolean automatizar, String fecha) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(META_COLUMN_ID, id);
        values.put(META_COLUMN_MONTO, monto);
        values.put(META_COLUMN_TIPO, tipo);
        values.put(META_COLUMN_CONCEPTO, concepto);
        values.put(META_COLUMN_AUTOMATIZAR, automatizar);
        values.put(META_COLUMN_FECHA, fecha);

        db.insert(META_TABLE_NAME, null, values);
    }

    public void insertTarjeta(int id, double monto, String tipo, String concepto, int fourdigits, String corte, double interes) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TARJETA_COLUMN_ID, id);
        values.put(TARJETA_COLUMN_MONTO, monto);
        values.put(TARJETA_COLUMN_BANCO, tipo);
        values.put(TARJETA_COLUMN_VENCIMIENTO, concepto);
        values.put(TARJETA_COLUMN_FOURDIGITS, fourdigits);
        values.put(TARJETA_COLUMN_CORTE, corte);
        values.put(TARJETA_COLUMN_INTERES, interes);

        db.insert(TARJETA_TABLE_NAME, null, values);
    }

    public String getUsuario() {
        String countQuery = "SELECT nombre  FROM " + USUARIO_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int cnt = cursor.getCount();
        cursor.close();

        return countQuery;
    }

    /*
    public List<Juego> getAllGames(){
        List<Juego> juegos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GAME_TABLE_NAME, ALL_COLUMNS_GAME, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Juego juego = cursorToGames(cursor);
            juegos.add(juego);
            cursor.moveToNext();
        }
        cursor.close();

        return juegos;
    }

    private Juego cursorToGames(Cursor cursor){
        Juego juego = new Juego();

        juego.setNombre(cursor.getString(1));
        juego.setDescripcion(cursor.getString(2));

        return juego;
    }


    public List<Equipo> getAllTeams(){
        List<Equipo> equipos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TEAM_TABLE_NAME, ALL_COLUMNS_TEAM, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Equipo equipo = cursorToEquipos(cursor);
            equipos.add(equipo);
            cursor.moveToNext();
        }
        cursor.close();

        return equipos;
    }

    private Equipo cursorToEquipos(Cursor cursor){
        Equipo equipo = new Equipo();

        equipo.setNombre(cursor.getString(1));
        equipo.setDescripcion(cursor.getString(2));

        return equipo;
    }

    public List<Partida> getAllPartidas(String juego){
        List<Partida> partidas = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                PARTIDA_TABLE_NAME,
                ALL_COLUMNS_PARTIDA,
                "juego = ?",
                new String[] {juego},
                null,
                null,
                null
        );

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Partida partida = cursorToPartidas(cursor);
            partidas.add(partida);
            cursor.moveToNext();
        }
        cursor.close();

        return partidas;
    }

    private Partida cursorToPartidas(Cursor cursor){
        Partida partida = new Partida();

        partida.setID(cursor.getInt(0));
        partida.setJuego(cursor.getString(1));
        partida.setEquipo_A(cursor.getString(2));
        partida.setEquipo_B(cursor.getString(3));
        partida.setPuntaje_equipo_A(cursor.getInt(4));
        partida.setPuntaje_equipo_B(cursor.getInt(5));

        return partida;
    }

    public ArrayList<String> getDetalleDePartidaByID(String id){
        ArrayList<String> lista = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                DETALLE_TABLE_NAME,
                ALL_COLUMNS_DETALLE,
                "id = ?",
                new String[] {id},
                null,
                null,
                null
        );

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            String partida = cursorToDetalle(cursor);
            lista.add(partida);
            cursor.moveToNext();
        }
        cursor.close();

        return lista;
    }

    private String cursorToDetalle(Cursor cursor){
        String jugada = "";

        jugada += cursor.getString(1);
        jugada += ": ";
        jugada += cursor.getString(3);
        jugada += "\t\t\t\t";
        jugada += cursor.getString(2);
        jugada += ": ";
        jugada += cursor.getString(4);

        return jugada;
    }

    public int getCantidadDePartidas() {
        String countQuery = "SELECT  * FROM " + PARTIDA_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int cnt = cursor.getCount();
        cursor.close();

        return cnt;
    }

    public Boolean gameExists(String nombre){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT nombre FROM " +
                        GAME_TABLE_NAME + " WHERE " +
                        GAME_COLUMN_NAME + " = ?",
                new String[] {nombre}
        );

        if (cursor.getCount() <= 0){
            //este juego no esta registrado
            return false;
        }
        else{
            //este juego si esta registrado
            return true;
        }

    }

    public Boolean teamExists(String equipo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT nombre FROM " +
                        TEAM_TABLE_NAME + " WHERE " +
                        TEAM_COLUMN_NAME + " = ?",
                new String[] {equipo}
        );

        if (cursor.getCount() <= 0){
            //este juego no esta registrado
            return false;
        }
        else{
            //este juego si esta registrado
            return true;
        }
    }

    public Boolean partidaExists(String juego, String equipoA, String equipoB){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " +
                        PARTIDA_TABLE_NAME + " WHERE " +
                        PARTIDA_COLUMN_JUEGO + " = ? AND " +
                        PARTIDA_COLUMN_EQUIPO_A + " = ? AND " +
                        PARTIDA_COLUMN_EQUIPO_B + " = ?",
                new String[] {juego, equipoA, equipoB}
        );

        if (cursor.getCount() <= 0){
            //esta partida no esta registrado
            return false;
        }
        else{
            //esta partida si esta registrado
            return true;
        }

    }
    */
}
