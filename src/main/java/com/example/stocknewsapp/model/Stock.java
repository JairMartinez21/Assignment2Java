package com.example.stocknewsapp.model;

import com.google.gson.annotations.SerializedName;

public class Stock {
    @SerializedName("01. symbol")
    private String symbol;
    @SerializedName("05. price")
    private String price;
    @SerializedName("06. volume")
    private String volume;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", price='" + price + '\'' +
                ", volume='" + volume + '\'' +
                '}';
    }
}
