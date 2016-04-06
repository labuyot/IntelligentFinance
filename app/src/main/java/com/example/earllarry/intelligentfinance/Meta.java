package com.example.earllarry.intelligentfinance;

/**
 * Created by EarlLarry on 15-Feb-16.
 */
public class Meta {

    private int id;
    private String concepto;
    private double monto;
    private double ahorrado;
    private String fechainicio;
    private String fechafinal;

    public Meta() {

    }

    public Meta(int id, String concepto, double monto, double ahorrado, String fechainicio, String fechafinal) {
        this.id = id;
        this.concepto = concepto;
        this.monto = monto;
        this.ahorrado = ahorrado;
        this.fechainicio = fechainicio;
        this.fechafinal = fechafinal;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getConcepto() { return concepto; }

    public void setConcepto(String concepto) { this.concepto = concepto; }

    public double getMonto() { return monto; }

    public void setMonto(double monto) { this.monto = monto; }

    public double getAhorrado() { return ahorrado; }

    public void setAhorrado(double ahorrado) { this.ahorrado = ahorrado; }

    public String getFechaInicio() { return fechainicio; }

    public void setFechaInicio(String fechainicio) { this.fechainicio = fechainicio; }

    public String getFechaFinal() {
        return fechafinal;
    }

    public void setFechaFinal(String fechafinal) { this.fechafinal = fechafinal; }
}
