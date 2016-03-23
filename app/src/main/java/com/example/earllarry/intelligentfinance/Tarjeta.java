package com.example.earllarry.intelligentfinance;

/**
 * Created by EarlLarry on 15-Feb-16.
 */
public class Tarjeta {

    private int id;
    private String banco;
    private double monto;
    private  double consumo;
    private int fourdigits;
    private double interes;
    private String corte;
    private String vencimiento;

    public Tarjeta() {

    }

    public Tarjeta(int id, String banco, double monto, double consumo, int fourdigits, double interes, String corte, String vencimiento) {
        this.id = id;
        this.banco = banco;
        this.monto = monto;
        this.consumo = consumo;
        this.fourdigits = fourdigits;
        this.interes = interes;
        this.corte = corte;
        this.vencimiento = vencimiento;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getBanco() { return banco; }

    public void setBanco(String banco) { this.banco = banco; }

    public double getMonto() { return monto; }

    public void setMonto(double monto) { this.monto = monto; }

    public double getConsumo() { return consumo; }

    public void setConsumo(double consumo) { this.consumo = consumo; }

    public int getFourdigits() { return fourdigits; }

    public void setFourdigits(int fourdigits) { this.fourdigits = fourdigits; }

    public double getInteres() { return interes; }

    public void setInteres(double interes) { this.interes = interes; }

    public String getCorte() { return corte; }

    public void setCorte(String corte) { this.corte = corte; }

    public String getVencimiento() { return vencimiento; }

    public void setVencimiento(String vencimiento) { this.vencimiento = vencimiento; }

}
