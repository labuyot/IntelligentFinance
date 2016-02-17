package com.example.earllarry.intelligentfinance;

/**
 * Created by EarlLarry on 15-Feb-16.
 */
public class Gasto {

    private String Ingreso;
    private double monto;
    private String concepto;
    private String tipo;
    private int automatizar;
    private String fecha;

    public Gasto() {

    }

    public Gasto(String ingreso, double monto, String concepto, String tipo, int automatizar, String fecha) {
        Ingreso = ingreso;
        this.monto = monto;
        this.concepto = concepto;
        this.tipo = tipo;
        this.automatizar = automatizar;
        this.fecha = fecha;
    }

    public String getIngreso() {
        return Ingreso;
    }

    public void setIngreso(String ingreso) {
        Ingreso = ingreso;
    }

    public double getMonto() { return monto; }

    public void setMonto(double monto) { this.monto = monto; }

    public String getConcepto() { return concepto; }

    public void setConcepto(String concepto) { this.concepto = concepto; }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int isAutomatizar() {
        return automatizar;
    }

    public void setAutomatizar(int automatizar) {
        this.automatizar = automatizar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
