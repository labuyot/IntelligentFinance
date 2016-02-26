package com.example.earllarry.intelligentfinance;

/**
 * Created by EarlLarry on 15-Feb-16.
 */
public class Ingreso {

    private String concepto;
    private double monto;
    private int automatizar;
    private String fecha;
    private String frecuencia;

    public Ingreso() {

    }

    public Ingreso(String concepto, double monto, int automatizar, String fecha, String frecuencia) {
        this.concepto = concepto;
        this.monto = monto;
        this.automatizar = automatizar;
        this.fecha = fecha;
        this.frecuencia = frecuencia;
    }

    public String getConcepto() { return concepto; }

    public void setConcepto(String concepto) { this.concepto = concepto; }

    public double getMonto() { return monto; }

    public void setMonto(double monto) { this.monto = monto; }

    public int getAutomatizar() { return automatizar; }

    public void setAutomatizar(int automatizar) { this.automatizar = automatizar; }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFrecuencia() { return frecuencia; }

    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }
}
