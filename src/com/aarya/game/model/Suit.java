package com.aarya.game.model;

public enum Suit {
    DIAMOND("♢"), HEART("♡"), SPADE("♤"), CLUB("♧");

    private String symbol;

    Suit(String s) {
        this.symbol = s;
    }

    public String getSymbol() {
        return this.symbol;
    }
}