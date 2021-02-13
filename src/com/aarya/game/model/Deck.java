package com.aarya.game.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
        for (Rank r : Rank.values()) {
            for (Suit s : Suit.values()) {
                Card card = new Card(r, s);
                deck.add(card);
            }
        }
        Collections.shuffle(deck);
    }

    public List<Card> getDeck() {
        return this.deck;
    }

    public int size() {
        return this.deck.size();
    }

    public Card drawCard() {
        return deck.remove(0);
    }

    public List<Card> drawCards(int num) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            cards.add(deck.remove(0));
        }
        return cards;
    }

    public void show() {
        int n = 0;
        for (Card card : this.deck) {
            System.out.println("Card # " + n++ + ": " + card.toString());
        }
    }

}
