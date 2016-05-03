package com.example.earllarry.intelligentfinance;

/**
 * Created by EarlLarry on 02-May-16.
 */
public class Deuda {

    private int id;
    private String concepto;
    private double monto;
    private double status;
    private String tipo;
    private String fecha;

    public Deuda() {

    }

    public Deuda(int id, String concepto, double monto, double status, String tipo, String fecha) {
        this.id = id;
        this.concepto = concepto;
        this.monto = monto;
        this.status = status;
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getConcepto() { return concepto; }

    public void setConcepto(String concepto) { this.concepto = concepto; }

    public double getMonto() { return monto; }

    public void setMonto(double monto) { this.monto = monto; }

    public double getStatus() { return status; }

    public void setStatus(double status) { this.status = status; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) { this.fecha = fecha; }

}
