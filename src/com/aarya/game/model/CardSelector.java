package com.aarya.game.model;

import java.util.ArrayList;
import java.util.List;

public class CardSelector {

    private final List<House> houses;
    private final List<Card> cards;
    private Card playerCard;

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

    public void setPlayerCard(Card card) {
        this.playerCard = card;
    }

    public Card getPlayerCard() {
        return this.playerCard;
    }

    public boolean hasCards() {
        return !this.cards.isEmpty();
    }

    public boolean hasHouses() { return !this.houses.isEmpty(); }

    public boolean hasSelectedCard() { return this.playerCard != null; }

    public List<Card> getAllCards() {
        List<Card> c = new ArrayList<>();
        c.add(this.playerCard);
        c.addAll(this.cards);
        return c;
    }

    public int getCaptureValue() {
        int c = 0;

        c += this.playerCard.getCaptureValue();

        for (House house : this.houses) {
            c += house.getCaptureValue();
        }

        for (Card card : this.cards) {
            c += card.getCaptureValue();
        }
        return c;
    }
}
