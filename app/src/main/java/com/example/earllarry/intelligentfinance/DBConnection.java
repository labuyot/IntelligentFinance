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

    public static final String DATABASE_NAME = "IntelligentFinance.db";
    private static final int DATABASE_VERSION = 3;
    private SQLiteDatabase db;

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
    private static final String TARJETA_COLUMN_FOURDIGITS = "cuatrodigitos";
    private static final String TARJETA_COLUMN_INTERES = "interes";
    private static final String TARJETA_COLUMN_CORTE = "corte";
    private static final String TARJETA_COLUMN_VENCIMIENTO = "vencimiento";

    private static final String META_TABLE_NAME = "Meta";
    private static final String META_COLUMN_ID = "id";
    private static final String META_COLUMN_CONCEPTO = "concepto";
    private static final String META_COLUMN_MONTO = "monto";
    private static final String META_COLUMN_TIPO = "tipo";
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
            META_COLUMN_FECHA
    };

    public static final String CREATE_USUARIO_TABLE =
            "CREATE TABLE " +
                    USUARIO_TABLE_NAME +
                    "( " +
                    USUARIO_COLUMN_NAME + " text " +
                    ")";

    public static final String CREATE_INGRESO_TABLE =
            "CREATE TABLE " +
                    INGRESO_TABLE_NAME +
                    "( " +
                    INGRESO_COLUMN_ID + " integer primary key autoincrement, " +
                    INGRESO_COLUMN_CONCEPTO + " text, " +
                    INGRESO_COLUMN_TIPO + " text, " +
                    INGRESO_COLUMN_MONTO + " real, " +
                    INGRESO_COLUMN_AUTOMATIZAR + " integer, " +
                    INGRESO_COLUMN_FECHA + " text " +
                    ")";

    public static final String CREATE_GASTO_TABLE =
            "CREATE TABLE " +
                    GASTO_TABLE_NAME +
                    "( " +
                    GASTO_COLUMN_ID + " integer primary key autoincrement, " +
                    GASTO_COLUMN_CONCEPTO + " text, " +
                    GASTO_COLUMN_TIPO + " text, " +
                    GASTO_COLUMN_MONTO + " real, " +
                    GASTO_COLUMN_AUTOMATIZAR + " integer, " +
                    GASTO_COLUMN_FECHA + " text " +
                    ")";

    public static final String CREATE_META_TABLE =
            "CREATE TABLE " +
                    META_TABLE_NAME +
                    "( " +
                    META_COLUMN_ID + " integer primary key autoincrement, " +
                    META_COLUMN_CONCEPTO + " text, " +
                    META_COLUMN_TIPO + " text, " +
                    META_COLUMN_MONTO + " real, " +
                    META_COLUMN_FECHA + " text " +
                    ")";

    public static final String CREATE_TARJETA_TABLE =
            "CREATE TABLE " +
                    TARJETA_TABLE_NAME +
                    "( " +
                    TARJETA_COLUMN_ID + " integer primary key autoincrement, " +
                    TARJETA_COLUMN_BANCO + " text, " +
                    TARJETA_COLUMN_VENCIMIENTO + " text, " +
                    TARJETA_COLUMN_MONTO + " real, " +
                    TARJETA_COLUMN_FOURDIGITS + " integer, " +
                    TARJETA_COLUMN_CORTE + " text, " +
                    TARJETA_COLUMN_INTERES + " real " +
                    ")";

    public DBConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
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
        values.put(META_COLUMN_FECHA, fecha);

        db.insert(META_TABLE_NAME, null, values);
    }

    public void insertTarjeta(int id, double monto, String tipo, String vencimiento, int fourdigits, String corte, double interes) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TARJETA_COLUMN_ID, id);
        values.put(TARJETA_COLUMN_MONTO, monto);
        values.put(TARJETA_COLUMN_BANCO, tipo);
        values.put(TARJETA_COLUMN_VENCIMIENTO, vencimiento);
        values.put(TARJETA_COLUMN_FOURDIGITS, fourdigits);
        values.put(TARJETA_COLUMN_CORTE, corte);
        values.put(TARJETA_COLUMN_INTERES, interes);

        db.insert(TARJETA_TABLE_NAME, null, values);
    }

    public List<Usuario> getAllUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(USUARIO_TABLE_NAME, ALL_COLUMNS_USUARIO, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Usuario usuario = cursorToUsuarios(cursor);
            usuarios.add(usuario);
            cursor.moveToNext();
        }
        cursor.close();

        return usuarios;
    }

    private Usuario cursorToUsuarios(Cursor cursor){
        Usuario usuario = new Usuario();

        usuario.setNombre(cursor.getString(0));

        return usuario;
    }

    public List<Ingreso> getAllIngresos(){
        List<Ingreso> ingresos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(INGRESO_TABLE_NAME, ALL_COLUMNS_INGRESO, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Ingreso ingreso = cursorToIngresos(cursor);
            ingresos.add(ingreso);
            cursor.moveToNext();
        }
        cursor.close();

        return ingresos;
    }

    private Ingreso cursorToIngresos(Cursor cursor){
        Ingreso ingreso = new Ingreso();

        ingreso.setIngreso(cursor.getString(0));
        ingreso.setMonto(cursor.getDouble(1));
        ingreso.setConcepto(cursor.getString(2));
        ingreso.setTipo(cursor.getString(3));
        ingreso.setAutomatizar(cursor.getInt(4));
        ingreso.setFecha(cursor.getString(5));

        return ingreso;
    }

    public List<Gasto> getAllGastos(){
        List<Gasto> gastos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(GASTO_TABLE_NAME, ALL_COLUMNS_GASTO, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Gasto gasto = cursorToGastos(cursor);
            gastos.add(gasto);
            cursor.moveToNext();
        }
        cursor.close();

        return gastos;
    }

    private Gasto cursorToGastos(Cursor cursor){
        Gasto gasto = new Gasto();

        gasto.setIngreso(cursor.getString(0));
        gasto.setMonto(cursor.getDouble(1));
        gasto.setConcepto(cursor.getString(2));
        gasto.setTipo(cursor.getString(3));
        gasto.setAutomatizar(cursor.getInt(4));
        gasto.setFecha(cursor.getString(5));

        return gasto;
    }

    public List<Meta> getAllMetas(){
        List<Meta> metas = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(META_TABLE_NAME, ALL_COLUMNS_META, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Meta meta = cursorToMetas(cursor);
            metas.add(meta);
            cursor.moveToNext();
        }
        cursor.close();

        return metas;
    }

    private Meta cursorToMetas(Cursor cursor){
        Meta meta = new Meta();

        meta.setIngreso(cursor.getString(0));
        meta.setMonto(cursor.getDouble(1));
        meta.setConcepto(cursor.getString(2));
        meta.setTipo(cursor.getString(3));
        meta.setFecha(cursor.getString(5));

        return meta;
    }

    public List<Tarjeta> getAllTarjetas(){
        List<Tarjeta> tarjetas = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TARJETA_TABLE_NAME, ALL_COLUMNS_TARJETA, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Tarjeta tarjeta = cursorToTarjetas(cursor);
            tarjetas.add(tarjeta);
            cursor.moveToNext();
        }
        cursor.close();

        return tarjetas;
    }

    private Tarjeta cursorToTarjetas(Cursor cursor){
        Tarjeta tarjeta = new Tarjeta();

        tarjeta.setMonto(cursor.getDouble(0));
        tarjeta.setBanco(cursor.getString(1));
        tarjeta.setVencimiento(cursor.getString(2));
        tarjeta.setFourdigits(cursor.getInt(3));
        tarjeta.setCorte(cursor.getString(4));
        tarjeta.setInteres(cursor.getDouble(5));

        return tarjeta;
    }

    /*
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
