package com.example.earllarry.intelligentfinance;

/**
 * Created by EarlLarry on 15-Feb-16.
 */
public class Meta {

    private String concepto;
    private double monto;
    private String fechainicio;
    private String fechafinal;

    public Meta() {

    }

    public Meta(String concepto, double monto, String fechainicio, String fechafinal) {
        this.concepto = concepto;
        this.monto = monto;
        this.fechainicio = fechainicio;
        this.fechafinal = fechafinal;
    }

    public String getConcepto() { return concepto; }

    public void setConcepto(String concepto) { this.concepto = concepto; }

    public double getMonto() { return monto; }

    public void setMonto(double monto) { this.monto = monto; }

    public String getFechaInicio() { return fechainicio; }

    public void setFechaInicio(String fechainicio) { this.fechainicio = fechainicio; }

    public String getFechaFinal() {
        return fechafinal;
    }

    public void setFechaFinal(String fechafinal) { this.fechafinal = fechafinal; }
}
