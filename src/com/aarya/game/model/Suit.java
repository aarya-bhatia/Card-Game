package com.aarya.game.model;

public enum Suit {
    DIAMOND("♢","D"), HEART("♡","H"), SPADE("♤","S"), CLUB("♧","C");

    private String symbol;
    private String name;

    Suit(String s, String v) {
        this.symbol = s;
        this.name = v;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getName() { return this.name; }
}