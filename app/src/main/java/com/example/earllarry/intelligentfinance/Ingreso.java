package com.example.earllarry.intelligentfinance;

/**
 * Created by EarlLarry on 15-Feb-16.
 */
public class Ingreso {

    public static final String INGRESO_TABLE_NAME = "Ingreso";
    public static final String INGRESO_COLUMN_ID = "id";
    public static final String INGRESO_COLUMN_CONCEPTO = "concepto";
    public static final String INGRESO_COLUMN_MONTO = "monto";
    public static final String INGRESO_COLUMN_TIPO = "tipo";
    public static final String INGRESO_COLUMN_AUTOMATIZAR = "automatizar";
    public static final String INGRESO_COLUMN_FECHA = "fecha";

    public static final String[] ALL_COLUMNS_INGRESO = new String[] {
            INGRESO_COLUMN_ID,
            INGRESO_COLUMN_MONTO,
            INGRESO_COLUMN_TIPO,
            INGRESO_COLUMN_CONCEPTO,
            INGRESO_COLUMN_AUTOMATIZAR,
            INGRESO_COLUMN_FECHA
    };

    public static final String CREATE_INGRESO_TABLE =
            "CREATE TABLE " +
                    INGRESO_TABLE_NAME +
                    "( " +
                    INGRESO_COLUMN_ID + " integer primary key autoincrement, " +
                    INGRESO_COLUMN_CONCEPTO + " text, " +
                    INGRESO_COLUMN_TIPO + " text " +
                    INGRESO_COLUMN_MONTO + " real, " +
                    INGRESO_COLUMN_AUTOMATIZAR + " integer " +
                    INGRESO_COLUMN_FECHA + " text " +
                    ")";

    private int id;
    private String Ingreso;
    private String concepto;
    private double monto;
    private String tipo;
    private boolean automatizar;
    private String fecha;

    public Ingreso(String ingreso, String concepto, double monto, String tipo, boolean automatizar, String fecha) {
        Ingreso = ingreso;
        this.concepto = concepto;
        this.monto = monto;
        this.tipo = tipo;
        this.automatizar = automatizar;
        this.fecha = fecha;
    }

    public Ingreso() {

    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getIngreso() {
        return Ingreso;
    }

    public void setIngreso(String ingreso) {
        Ingreso = ingreso;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isAutomatizar() {
        return automatizar;
    }

    public void setAutomatizar(boolean automatizar) {
        this.automatizar = automatizar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
