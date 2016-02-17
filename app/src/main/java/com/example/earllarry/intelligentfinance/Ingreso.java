package com.example.earllarry.intelligentfinance;

/**
 * Created by EarlLarry on 15-Feb-16.
 */
public class Ingreso {

    private int id;
    private String Ingreso;
    private String concepto;
    private double monto;
    private String tipo;
    private boolean automatizar;
    private String fecha;

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
