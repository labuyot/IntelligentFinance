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
    private static final int DATABASE_VERSION = 4;
    private SQLiteDatabase db;

    private static final String USUARIO_TABLE_NAME = "Usuario";
    private static final String USUARIO_COLUMN_NAME = "nombre";

    private static final String TARJETAGASTO_TABLE_NAME = "Tarjetagasto";
    private static final String TARJETAGASTO_COLUMN_CUATRODIGITOS = "Cuatrodigitos";
    private static final String TARJETAGASTO_COLUMN_GASTO = "Gasto";
    private static final String TARJETAGASTO_COLUMN_CONCEPTO = "Concepto";

    private static final String INGRESO_TABLE_NAME = "Ingreso";
    private static final String INGRESO_COLUMN_ID = "Id";
    private static final String INGRESO_COLUMN_CONCEPTO = "Concepto";
    private static final String INGRESO_COLUMN_MONTO = "Monto";
    private static final String INGRESO_COLUMN_AUTOMATIZAR = "Automatizar";
    private static final String INGRESO_COLUMN_FECHA = "Fecha";
    private static final String INGRESO_COLUMN_FRECUENCIA = "Frecuencia";

    private static final String GASTO_TABLE_NAME = "Gasto";
    private static final String GASTO_COLUMN_ID = "Id";
    private static final String GASTO_COLUMN_CONCEPTO = "Concepto";
    private static final String GASTO_COLUMN_MONTO = "Monto";
    private static final String GASTO_COLUMN_TIPO = "Tipo";
    private static final String GASTO_COLUMN_AUTOMATIZAR = "Automatizar";
    private static final String GASTO_COLUMN_FECHA = "Fecha";
    private static final String GASTO_COLUMN_FRECUENCIA = "Frecuencia";

    private static final String TARJETA_TABLE_NAME = "Tarjeta";
    private static final String TARJETA_COLUMN_ID = "Id";
    private static final String TARJETA_COLUMN_BANCO = "Banco";
    private static final String TARJETA_COLUMN_MONTO = "Monto";
    private static final String TARJETA_COLUMN_CONSUMO = "Consumo";
    private static final String TARJETA_COLUMN_FOURDIGITS = "Cuatrodigitos";
    private static final String TARJETA_COLUMN_INTERES = "Interes";
    private static final String TARJETA_COLUMN_CORTE = "Corte";
    private static final String TARJETA_COLUMN_VENCIMIENTO = "Vencimiento";

    private static final String META_TABLE_NAME = "Meta";
    private static final String META_COLUMN_ID = "Id";
    private static final String META_COLUMN_CONCEPTO = "Concepto";
    private static final String META_COLUMN_MONTO = "Monto";
    private static final String META_COLUMN_AHORRO = "Ahorro";
    private static final String META_COLUMN_FECHAINICIO = "Fechainicio";
    private static final String META_COLUMN_FECHAFINAL = "Fechafinal";

    public static final String[] ALL_COLUMNS_USUARIO = new String[] {
            USUARIO_COLUMN_NAME,
    };

    public static final String[] ALL_COLUMNS_TARJETAGASTO = new String[] {
            TARJETAGASTO_COLUMN_CUATRODIGITOS,
            TARJETAGASTO_COLUMN_GASTO,
            TARJETAGASTO_COLUMN_CONCEPTO
    };

    public static final String[] ALL_COLUMNS_INGRESO = new String[] {
            INGRESO_COLUMN_ID,
            INGRESO_COLUMN_CONCEPTO,
            INGRESO_COLUMN_MONTO,
            INGRESO_COLUMN_AUTOMATIZAR,
            INGRESO_COLUMN_FECHA,
            INGRESO_COLUMN_FRECUENCIA
    };

    public static final String[] ALL_COLUMNS_GASTO = new String[] {
            GASTO_COLUMN_ID,
            GASTO_COLUMN_CONCEPTO,
            GASTO_COLUMN_MONTO,
            GASTO_COLUMN_TIPO,
            GASTO_COLUMN_AUTOMATIZAR,
            GASTO_COLUMN_FECHA,
            GASTO_COLUMN_FRECUENCIA
    };

    public static final String[] ALL_COLUMNS_TARJETA = new String[] {
            TARJETA_COLUMN_ID,
            TARJETA_COLUMN_BANCO,
            TARJETA_COLUMN_MONTO,
            TARJETA_COLUMN_CONSUMO,
            TARJETA_COLUMN_FOURDIGITS,
            TARJETA_COLUMN_INTERES,
            TARJETA_COLUMN_CORTE,
            TARJETA_COLUMN_VENCIMIENTO
    };

    public static final String[] ALL_COLUMNS_META = new String[] {
            META_COLUMN_ID,
            META_COLUMN_CONCEPTO,
            META_COLUMN_MONTO,
            META_COLUMN_AHORRO,
            META_COLUMN_FECHAINICIO,
            META_COLUMN_FECHAFINAL
    };

    public static final String CREATE_USUARIO_TABLE =
            "CREATE TABLE " +
                    USUARIO_TABLE_NAME +
                    "( " +
                    USUARIO_COLUMN_NAME + " text " +
                    ")";

    public static final String CREATE_TARJETAGASTO_TABLE =
            "CREATE TABLE " +
                    TARJETAGASTO_TABLE_NAME +
                    "( " +
                    TARJETAGASTO_COLUMN_CUATRODIGITOS + " integer, " +
                    TARJETAGASTO_COLUMN_GASTO + " real, " +
                    TARJETAGASTO_COLUMN_CONCEPTO + " text " +
                    ")";

    public static final String CREATE_INGRESO_TABLE =
            "CREATE TABLE " +
                    INGRESO_TABLE_NAME +
                    "( " +
                    INGRESO_COLUMN_ID + " integer primary key autoincrement, " +
                    INGRESO_COLUMN_CONCEPTO + " text, " +
                    INGRESO_COLUMN_MONTO + " real, " +
                    INGRESO_COLUMN_AUTOMATIZAR + " integer, " +
                    INGRESO_COLUMN_FECHA + " text, " +
                    INGRESO_COLUMN_FRECUENCIA + " text " +
                    ")";

    public static final String CREATE_GASTO_TABLE =
            "CREATE TABLE " +
                    GASTO_TABLE_NAME +
                    "( " +
                    GASTO_COLUMN_ID + " integer primary key autoincrement, " +
                    GASTO_COLUMN_CONCEPTO + " text, " +
                    GASTO_COLUMN_MONTO + " real, " +
                    GASTO_COLUMN_TIPO + " text, " +
                    GASTO_COLUMN_AUTOMATIZAR + " integer, " +
                    GASTO_COLUMN_FECHA + " text, " +
                    GASTO_COLUMN_FRECUENCIA + " text " +
                    ")";

    public static final String CREATE_META_TABLE =
            "CREATE TABLE " +
                    META_TABLE_NAME +
                    "( " +
                    META_COLUMN_ID + " integer primary key autoincrement, " +
                    META_COLUMN_CONCEPTO + " text, " +
                    META_COLUMN_MONTO + " real, " +
                    META_COLUMN_AHORRO + " real, " +
                    META_COLUMN_FECHAINICIO + " text, " +
                    META_COLUMN_FECHAFINAL + " text " +
                    ")";

    public static final String CREATE_TARJETA_TABLE =
            "CREATE TABLE " +
                    TARJETA_TABLE_NAME +
                    "( " +
                    TARJETA_COLUMN_ID + " integer primary key autoincrement, " +
                    TARJETA_COLUMN_BANCO + " text, " +
                    TARJETA_COLUMN_MONTO + " real, " +
                    TARJETA_COLUMN_CONSUMO + " real, " +
                    TARJETA_COLUMN_FOURDIGITS + " integer, " +
                    TARJETA_COLUMN_INTERES + " real, " +
                    TARJETA_COLUMN_CORTE + " text, " +
                    TARJETA_COLUMN_VENCIMIENTO + " text " +
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
        db.execSQL(CREATE_TARJETAGASTO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + INGRESO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GASTO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + META_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TARJETA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USUARIO_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TARJETAGASTO_TABLE_NAME);
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

    public void insertTarjetaGasto(int cuatroDigits, double gasto, String concepto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TARJETAGASTO_COLUMN_CUATRODIGITOS, cuatroDigits);
        values.put(TARJETAGASTO_COLUMN_GASTO, gasto);
        values.put(TARJETAGASTO_COLUMN_CONCEPTO, concepto);

        db.insert(TARJETAGASTO_TABLE_NAME, null, values);
    }

    public void insertIngreso(String concepto, double monto, boolean automatizar, String fecha, String frecuencia) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(INGRESO_COLUMN_CONCEPTO, concepto);
        values.put(INGRESO_COLUMN_MONTO, monto);
        values.put(INGRESO_COLUMN_AUTOMATIZAR, automatizar);
        values.put(INGRESO_COLUMN_FECHA, fecha);
        values.put(INGRESO_COLUMN_FRECUENCIA, frecuencia);

        db.insert(INGRESO_TABLE_NAME, null, values);
    }

    public void insertGasto(String concepto, double monto, String tipo, boolean automatizar, String fecha, String frecuencia) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(GASTO_COLUMN_CONCEPTO, concepto);
        values.put(GASTO_COLUMN_MONTO, monto);
        values.put(GASTO_COLUMN_TIPO, tipo);
        values.put(GASTO_COLUMN_AUTOMATIZAR, automatizar);
        values.put(GASTO_COLUMN_FECHA, fecha);
        values.put(GASTO_COLUMN_FRECUENCIA, frecuencia);

        db.insert(GASTO_TABLE_NAME, null, values);
    }

    public void insertMeta(String concepto, double monto, String fechainicio, String fechafinal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(META_COLUMN_CONCEPTO, concepto);
        values.put(META_COLUMN_MONTO, monto);
        values.put(META_COLUMN_FECHAINICIO, fechainicio);
        values.put(META_COLUMN_FECHAFINAL, fechafinal);

        db.insert(META_TABLE_NAME, null, values);
    }

    public void insertTarjeta(String banco, double monto, int fourdigits, double interes, String corte, String vencimiento) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TARJETA_COLUMN_BANCO, banco);
        values.put(TARJETA_COLUMN_MONTO, monto);
        values.put(TARJETA_COLUMN_FOURDIGITS, fourdigits);
        values.put(TARJETA_COLUMN_INTERES, interes);
        values.put(TARJETA_COLUMN_CORTE, corte);
        values.put(TARJETA_COLUMN_VENCIMIENTO, vencimiento);

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

        ingreso.setId(cursor.getInt(0));
        ingreso.setConcepto(cursor.getString(1));
        ingreso.setMonto(cursor.getDouble(2));
        ingreso.setAutomatizar(cursor.getInt(3));
        ingreso.setFecha(cursor.getString(4));
        ingreso.setFrecuencia(cursor.getString(5));

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

        gasto.setId(cursor.getInt(0));
        gasto.setConcepto(cursor.getString(1));
        gasto.setMonto(cursor.getDouble(2));
        gasto.setTipo(cursor.getString(3));
        gasto.setAutomatizar(cursor.getInt(4));
        gasto.setFecha(cursor.getString(5));
        gasto.setFrecuencia(cursor.getString(6));

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

        meta.setId(cursor.getInt(0));
        meta.setConcepto(cursor.getString(1));
        meta.setMonto(cursor.getDouble(2));
        meta.setAhorrado(cursor.getDouble(3));
        meta.setFechaInicio(cursor.getString(4));
        meta.setFechaFinal(cursor.getString(5));

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

        tarjeta.setId(cursor.getInt(0));
        tarjeta.setBanco(cursor.getString(1));
        tarjeta.setMonto(cursor.getDouble(2));
        tarjeta.setConsumo(cursor.getDouble(3));
        tarjeta.setFourdigits(cursor.getInt(4));
        tarjeta.setInteres(cursor.getDouble(5));
        tarjeta.setCorte(cursor.getString(6));
        tarjeta.setVencimiento(cursor.getString(7));

        return tarjeta;
    }

    public double getTotal(String columnName, String tableName) {

        double total =0;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT SUM(" + columnName +  ") FROM " + tableName, null);
        if(cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        return total;
    }

    public double getTarjetaMonto(int cuatroDigitos) {

        double total =0;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT Monto FROM Tarjeta WHERE Cuatrodigitos=" + cuatroDigitos, null);
        if(cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        return total;
    }

    public double getTarjetaConsumo(int cuatroDigitos) {

        double total =0;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT Consumo FROM Tarjeta WHERE Cuatrodigitos=" + cuatroDigitos, null);
        if(cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        return total;
    }

    public double getTotalEfectivo(String columnName, String tableName) {

        double total =0;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT SUM(" + columnName +  ") FROM " + tableName + " WHERE Tipo = 'Efectivo'", null);
        if(cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        return total;
    }

    public void deleteData(String tableName, int id) {
        SQLiteDatabase db = getReadableDatabase();

        db.delete(tableName, "Id=" + id, null);

    }

    public void deleteDataGasto(String tableName, String concepto) {
        SQLiteDatabase db = getReadableDatabase();

        db.delete(tableName, "Concepto='" + concepto +"'", null);

    }

    public void deleteDataGastoTarjetaId(int cuatroDigitos) {
        SQLiteDatabase db = getReadableDatabase();

        db.delete("Tarjetagasto", "Cuatrodigitos=" + cuatroDigitos, null);

    }

    public void deleteDataGastoTarjetaConcepto(String concepto) {
        SQLiteDatabase db = getReadableDatabase();

        db.delete("Tarjetagasto", "Concepto='" + concepto + "'", null);

    }

    public int getCantidadDeFilas(String tabla) {
        String countQuery = "SELECT  * FROM " + tabla;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int cnt = cursor.getCount();
        cursor.close();

        return cnt;
    }

    public Boolean fourDigitsExist(int digitos, String tabla, String columna){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT CuatroDigitos FROM " +
                        tabla + " WHERE " +
                        columna + " = " + digitos, null
        );

        if (cursor.getCount() <= 0){
            //este concepto no esta registrado
            return false;
        }
        else{
            //este concepto si esta registrado
            return true;
        }

    }

    public Boolean conceptoExist(String concepto, String tabla, String columna){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT Concepto FROM " +
                        tabla + " WHERE " +
                        columna + " = ?",
                new String[]{concepto}
        );

        if (cursor.getCount() <= 0){
            //este concepto no esta registrado
            return false;
        }
        else{
            //este concepto si esta registrado
            return true;
        }

    }

    public void updateDataIngreso(int id, ContentValues data) {
        SQLiteDatabase db = getReadableDatabase();

        db.update("Ingreso", data, "Id=" + id, null);

    }

    public void updateDataGasto(int id, ContentValues data) {
        SQLiteDatabase db = getReadableDatabase();

        db.update("Gasto", data, "Id=" + id, null);

    }

    public void updateDataMeta(int id, ContentValues data) {
        SQLiteDatabase db = getReadableDatabase();

        db.update("Meta", data, "Id=" + id, null);

    }

    public void updateDataMetaAhorro(int id, ContentValues data) {
        SQLiteDatabase db = getReadableDatabase();

        db.update("Meta", data, "Id=" + id, null);

    }

    public void updateDataTarjeta(int id, ContentValues data) {
        SQLiteDatabase db = getReadableDatabase();

        db.update("Tarjeta", data, "Id=" + id , null);

    }

    public void updateTarjetaConsumo(int cuatroDigitos, ContentValues data) {
        SQLiteDatabase db = getReadableDatabase();

        db.update("Tarjeta", data, "Cuatrodigitos=" + cuatroDigitos , null);

    }

    public void updateTarjetaConsumoId(int id, ContentValues data) {
        SQLiteDatabase db = getReadableDatabase();

        db.update("Tarjeta", data, "Id=" + id, null);

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
