package com.aarya.game.model;

import java.util.ArrayList;
import java.util.List;

public class CardSelector {

    private List<House> houses;
    private List<Card> cards;
    private Card selectedCard;

    public CardSelector() {
        this.houses = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    public List<House> getHouses() {
        return this.houses;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public int numCards() { return this.cards.size(); }

    public int numHouses() { return this.houses.size(); }

    public void select(House house) {
        this.houses.add(house);
    }

    public void deselect(House house) {
        this.houses.remove(house);
    }

    public void select(Card card) {
        this.cards.add(card);
    }

    public void deselect(Card card) {
        this.cards.remove(card);
    }

    public void setSelectedCard(Card card) {
        this.selectedCard = card;
    }

    public Card getSelectedCard() {
        return this.selectedCard;
    }

    public boolean hasCards() {
        return this.cards != null && !this.cards.isEmpty();
    }

    public boolean hasHouses() {
        return this.houses != null && !this.houses.isEmpty();
    }

    public boolean hasSelectedCard() { return this.selectedCard != null; }

}
