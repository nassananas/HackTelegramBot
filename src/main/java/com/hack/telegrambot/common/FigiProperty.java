package com.hack.telegrambot.common;

public class FigiProperty {

    private double figiMin;
    private double figiMax;
    private Figi figi;

    public FigiProperty(Figi figi) {
        this.figi = figi;

        switch (figi){
            case BTC:
                this.figiMin = 2000000;
                this.figiMax = 3000000;
                break;
            case ETH:
                this.figiMin = 150000;
                this.figiMax = 170000;
                break;
            case BNB:
                this.figiMin = 200;
                this.figiMax = 400;
                break;
            case DOGE:
                this.figiMin = 15;
                this.figiMax = 20;
                break;
            case DOT:
                this.figiMin = 900;
                this.figiMax = 1200;
                break;
            case ADA:
                this.figiMin = 70;
                this.figiMax = 140;
                break;
        }
    }

    public double getFigiMin() {
        return figiMin;
    }

    public void setFigiMin(double figiMin) {
        this.figiMin = figiMin;
    }

    public double getFigiMax() {
        return figiMax;
    }

    public void setFigiMax(double figiMax) {
        this.figiMax = figiMax;
    }
}
