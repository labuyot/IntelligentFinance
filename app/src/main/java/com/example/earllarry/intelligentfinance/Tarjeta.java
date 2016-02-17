package com.example.earllarry.intelligentfinance;

/**
 * Created by EarlLarry on 15-Feb-16.
 */
public class Tarjeta {

    private String banco;
    private double monto;
    private int fourdigits;
    private double interes;
    private String corte;
    private String vencimiento;

    public Tarjeta() {

    }

    public Tarjeta(String banco, double monto, int fourdigits, double interes, String corte, String vencimiento) {
        this.banco = banco;
        this.monto = monto;
        this.fourdigits = fourdigits;
        this.interes = interes;
        this.corte = corte;
        this.vencimiento = vencimiento;
    }

    public String getBanco() { return banco; }

    public void setBanco(String banco) { this.banco = banco; }

    public double getMonto() { return monto; }

    public void setMonto(double monto) { this.monto = monto; }

    public int getFourdigits() { return fourdigits; }

    public void setFourdigits(int fourdigits) { this.fourdigits = fourdigits; }

    public double getInteres() { return interes; }

    public void setInteres(double interes) { this.interes = interes; }

    public String getCorte() { return corte; }

    public void setCorte(String corte) { this.corte = corte; }

    public String getVencimiento() { return vencimiento; }

    public void setVencimiento(String vencimiento) { this.vencimiento = vencimiento; }

}
