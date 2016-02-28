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
    private String frecuencia;

    public Gasto() {

    }

    public Gasto(String concepto, double monto, String tipo, int automatizar, String fecha, String frecuencia) {
        this.concepto = concepto;
        this.monto = monto;
        this.tipo = tipo;
        this.automatizar = automatizar;
        this.fecha = fecha;
        this.frecuencia = frecuencia;
    }

    public String getConcepto() { return concepto; }

    public void setConcepto(String concepto) { this.concepto = concepto; }

    public double getMonto() { return monto; }

    public void setMonto(double monto) { this.monto = monto; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getAutomatizar() { return automatizar; }

    public void setAutomatizar(int automatizar) {
        this.automatizar = automatizar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFrecuencia() { return frecuencia; }

    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }
}
