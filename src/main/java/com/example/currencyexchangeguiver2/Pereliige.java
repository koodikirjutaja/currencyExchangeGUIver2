package com.example.currencyexchangeguiver2;

public class Pereliige extends RahaTulemus {

    // Isikuväljad
    private String pereliige;
    private double rahaKogus;
    private String algusValuuta;
    private String sihtValuuta;

    // Konstruktor
    public Pereliige(String pereliige, double rahaKogus, String algusValuuta, String sihtValuuta) {
        this.pereliige = pereliige;
        this.rahaKogus = rahaKogus;
        this.algusValuuta = algusValuuta;
        this.sihtValuuta = sihtValuuta;
    }

    // Get-set meetodid
    public String getPereliige() {
        return pereliige;
    }

    public void setPereliige(String pereliige) {
        this.pereliige = pereliige;
    }

    public double getRahaKogus() {
        return rahaKogus;
    }

    public void setRahaKogus(double rahaKogus) {
        this.rahaKogus = rahaKogus;
    }

    public String getAlgusValuuta() {
        return algusValuuta;
    }

    public void setAlgusValuuta(String algusValuuta) {
        this.algusValuuta = algusValuuta;
    }

    public String getSihtValuuta() {
        return sihtValuuta;
    }

    public void setSihtValuuta(String sihtValuuta) {
        this.sihtValuuta = sihtValuuta;
    }
}
