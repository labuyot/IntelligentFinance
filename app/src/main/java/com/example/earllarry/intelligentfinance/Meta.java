package com.example.earllarry.intelligentfinance;

/**
 * Created by EarlLarry on 15-Feb-16.
 */
public class Meta {

    private double monto;
    private String tipo;
    private String concepto;
    private String fecha;

    public Meta() {

    }

    public Meta(double monto, String tipo, String concepto, String fecha) {
        this.monto = monto;
        this.tipo = tipo;
        this.concepto = concepto;
        this.fecha = fecha;
    }

    public double getMonto() { return monto; }

    public void setMonto(double monto) { this.monto = monto; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getConcepto() { return concepto; }

    public void setConcepto(String concepto) { this.concepto = concepto; }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
