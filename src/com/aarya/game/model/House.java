package com.aarya.game.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class House implements Collectible, Iterable<House>, Serializable {

    private static final long serialVersionUID = 1L;

    private List<Card> cards;
    private List<House> children;

    private final Rank rank;

    private House parent;

    public House(Rank rank) {
        this.cards = new ArrayList<>();
        this.rank = rank;
        this.children = new LinkedList<>();
        this.parent = null;
    }

    public House getParent() {
        return parent;
    }

    public void setParent(House parent) {
        this.parent = parent;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Rank getRank() {
        return this.rank;
    }

    @Override
    public int getPoints() {
        return this.cards.stream().map(Card::getPoints).reduce(0, Integer::sum);
    }

    @Override
    public String toString() {
        return "House " + this.rank;
    }

    public void show() {
        this.cards.forEach(card -> System.out.println(card.toString()));
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<House> getChildren() {
        return this.children;
    }

    public void setChildren(List<House> children) {
        this.children = children;
    }

    /**
     * A minimum of two child houses are sufficient to 'close' a house
     * of rank over 9 because the total capture value will always be greater than 13.
     *
     * Note: An open house can be broken by a player but a closed house is stable.
     *
     * @return Tells whether a house is open or not.
     */
    public boolean isOpen() {
        return this.rank.equals(Rank.KING) || (this.rank.getValue() >= 9 && this.children.size() > 1);
    }

    /**
     * Makes the given house a child of current house.
     *
     * @param h the house
     */
    public void add(House h) {
        h.setParent(this);
        this.children.add(h);
    }

    /**
     * Deletes a house
     *
     * @param h the house
     */
    public static void remove(House h) {
        if (h == null) { return; }
        House parent = h.getParent();
        if(parent != null) {
            h.setParent(null);
            parent.getChildren().remove(h);
        }
    }

    public Iterator<House> iterator() {
        return new HouseIterator(this);
    }

    public void addCard(Card playerCard) {
        this.cards.add(playerCard);
    }

    public boolean removeChild(House h) {
        if(this.children != null) {
            return this.children.remove(h);
        }

        return false;
    }
}
