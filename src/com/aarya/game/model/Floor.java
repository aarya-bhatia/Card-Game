package com.aarya.game.model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents the playing space of the game
 */
public class Floor implements Serializable {

    private List<House> houses;
    private List<Card> cards;

    public Floor() {
        this.cards = new ArrayList<>();
        this.houses = new ArrayList<>();
    }

    public Floor(List<Card> cards) {
        this();
        this.setCards(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public List<House> getHouses() {
        return this.houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = new ArrayList<>(houses);
    }

    public int numCards() {
        return this.cards.size();
    }

    public int numHouses() {
        return this.houses.size();
    }

    public void show() {
        System.out.println("----------------------------------");
        System.out.println("[START] Floor [START]");

        System.out.println("----------------------------------");
        System.out.println("Cards");
        System.out.println("----------------------------------");

        if (!this.cards.isEmpty()) {
            this.cards.forEach(card -> System.out.println(card.toString()));
        } else {
            System.out.println("No cards available.");
        }

        System.out.println("----------------------------------");
        System.out.println("Houses");
        System.out.println("----------------------------------");

        if (!this.houses.isEmpty()) {
            this.houses.forEach(house -> {
                house.displayCards();
                System.out.println("----------------------------------");
            });
        } else {
            System.out.println("No houses available.");
        }

        System.out.println("[END] Floor [END]");
        System.out.println("----------------------------------");
    }

}

