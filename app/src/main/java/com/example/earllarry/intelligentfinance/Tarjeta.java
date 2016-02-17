package com.example.earllarry.intelligentfinance;

/**
 * Created by EarlLarry on 15-Feb-16.
 */
public class Tarjeta {

    private double monto;
    private String banco;
    private String vencimiento;
    private int fourdigits;
    private String corte;
    private double interes;

    public Tarjeta() {

    }

    public Tarjeta(double monto, String banco, String vencimiento, int fourdigits, String corte, double interes) {
        this.monto = monto;
        this.banco = banco;
        this.vencimiento = vencimiento;
        this.fourdigits = fourdigits;
        this.corte = corte;
        this.interes = interes;
    }

    public double getMonto() { return monto; }

    public void setMonto(double monto) { this.monto = monto; }

    public String getBanco() { return banco; }

    public void setBanco(String banco) { this.banco = banco; }

    public String getVencimiento() { return vencimiento; }

    public void setVencimiento(String vencimiento) { this.vencimiento = vencimiento; }

    public int getFourdigits() { return fourdigits; }

    public void setFourdigits(int fourdigits) { this.fourdigits = fourdigits; }

    public String getCorte() { return corte; }

    public void setCorte(String corte) { this.corte = corte; }

    public double getInteres() { return interes; }

    public void setInteres(double interes) { this.interes = interes; }
}
