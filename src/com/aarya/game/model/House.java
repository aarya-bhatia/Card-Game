package com.aarya.game.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class House implements Comparable<House>, Collectible, Iterable<House>, Serializable {

    private List<Card> cards;
    private List<House> children;

    private House parent;
    private final Rank rank;

    public House(Rank rank) {
        this.rank = rank;
        this.cards = new ArrayList<>();
        this.children = new LinkedList<>();
    }

    public House(Rank rank, List<Card> cards, List<House> children) {
        this(rank);
        this.setCards(cards);
        this.setChildren(children);
    }

    public House getParent() {
        return parent;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public List<House> getChildren() {
        return this.children;
    }

    @Override
    public Rank getRank() {
        return this.rank;
    }

    @Override
    public int getPoints() {
        return getPoints(this);
    }

    private int getPoints(House house) {
        int tmp = 0;
        for(Card card: house.cards) {
            tmp += card.getPoints();
        }
        for(House child: house.children) {
            tmp += child.getPoints();
        }
        return tmp;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setChildren(List<House> children) {
        this.children = children;
    }

    public void setParent(House parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "[House " + this.rank + " # " + this.hashCode() + " ]";
    }

    /**
     * Two children are sufficient to close a house.
     * An open house can be broken by a player but a closed house is stable.
     *
     * @return tells whether a house is open or not
     */
    public boolean isClosed() {
        return this.rank.equals(Rank.KING) || (this.rank.getValue() >= 9 && this.children.size() > 1);
    }

    public Iterator<House> iterator() {
        return new HouseIterator(this);
    }

    public void add(House child) {
        this.children.add(child);
    }

    public void remove(House child) {
        this.children.remove(child);
    }

    public void displayCards() {
        System.out.println("House: " + this);
        displayCards(this);
    }

    private void displayCards(House house) {
        for(Card card: house.cards) {
            System.out.println(card);
        }
        for(House child: house.children) {
            displayCards(child);
        }
    }

    @Override
    public int compareTo(House o) {
        return this.getRank().compareTo(o.getRank());
    }

    public int getCaptureValue() {
        int value = Card.getCaptureValue(this.cards);
        for(House child: this.children) {
            value += child.getCaptureValue();
        }
        return value;
    }
}
