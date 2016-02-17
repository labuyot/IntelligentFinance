package com.example.earllarry.intelligentfinance;

/**
 * Created by EarlLarry on 15-Feb-16.
 */
public class Gasto {

    private String concepto;
    private double monto;
    private String tipo;
    private int automatizar;
    private String fecha;

    public Gasto() {

    }

    public Gasto(String concepto, double monto, String tipo, int automatizar, String fecha) {
        this.monto = monto;
        this.concepto = concepto;
        this.tipo = tipo;
        this.automatizar = automatizar;
        this.fecha = fecha;
    }

    public String getConcepto() { return concepto; }

    public void setConcepto(String concepto) { this.concepto = concepto; }

    public double getMonto() { return monto; }

    public void setMonto(double monto) { this.monto = monto; }

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
