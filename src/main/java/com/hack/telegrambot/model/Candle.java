package com.hack.telegrambot.model;

import com.hack.telegrambot.common.Figi;
import java.sql.Timestamp;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Component
@Entity
public class Candle {
    @Id
    @GeneratedValue
    private int id;

    private Figi figi;
    //private String figi;
    private double low;
    private double high;
    private double open;
    private double close;
    private Timestamp openTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Figi getFigi() {
        return figi;
    }

    public void setFigi(Figi figi) {
        this.figi = figi;
    }

    //    public String getFigi() {
//        return figi;
//    }
//
//    public void setFigi(String figi) {
//        this.figi = figi;
//    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public Timestamp getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Timestamp openTime) {
        this.openTime = openTime;
    }
}
