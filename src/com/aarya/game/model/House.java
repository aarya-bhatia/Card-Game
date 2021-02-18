package com.aarya.game.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class House implements Comparable<House>, Collectible, Iterable<House>, Serializable {

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

    public House(Rank rank, List<Card> cards, List<House> houses) throws RankNotFoundException {
        int captureValue = 0;

        if(cards != null) {
            for (Card card : cards) {
                captureValue += card.getRank().getValue();
            }
        }

        if(houses != null) {
            for (House house: houses) {
                captureValue += house.getRank().getValue();
            }
        }

        Rank r = Rank.normaliseRank(captureValue);

        if(!Rank.isValidRank(r)) {
            throw new RankNotFoundException();
        }

        this.rank = r;
        this.cards = cards;
        this.children = houses;
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
        this.cards = new ArrayList<>(cards);
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
        return "[House " + this.rank + " # " + this.hashCode() + " ]";
    }

    public void show() {
        this.cards.forEach(card -> System.out.println(card));
    }

    public void addCards(List<Card> cards) throws RankMismatchException {
        if(cards == null) {
            return;
        }
        if (Card.getCaptureValue(cards) == this.getRank().getValue()) {
            this.cards.addAll(cards);
        }
        else {
            throw new RankMismatchException();
        }
    }

    public List<House> getChildren() {
        return this.children;
    }

    public void setChildren(List<House> children) {
        for(House h: children) {
            if(h.getRank().equals(this.getRank())) {
                this.children.add(h);
            }
        }
    }

    /**
     * A minimum of two child houses are sufficient to 'close' a house
     * of rank over 9 because the total capture value will always be greater than 13.
     *
     * Note: An open house can be broken by a player but a closed house is stable.
     *
     * @return Tells whether a house is open or not.
     */
    public boolean isClosed() {
        return this.rank.equals(Rank.KING) || (this.rank.getValue() >= 9 && this.children.size() > 1);
    }

    public boolean isEmpty() {
        return this.getCards().isEmpty();
    }

    /**
     * Makes the given house a child of current house.
     *
     * @param h the house
     * @throws RankMismatchException rank is different
     */
    public void add(House h) throws RankMismatchException {
        if(h == null || h.isEmpty()) {
            System.out.println("Cannot add house: " + h + ", because it is null or empty.");
            return;
        }

        if(h.getRank().equals(this.getRank())) {
            h.setParent(this);
            this.children.add(h);
        }
        else {
            throw new RankMismatchException();
        }
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
        else {
            throw new RuntimeException("Cannot remove house: " + h.toString());
        }
    }

    public static void destroy(House house) {
        for(House h: house) {
            if(h == house) {
                continue;
            }
            System.out.println("Deleting House: " + h.toString());
            remove(h);
        }
    }

    public Iterator<House> iterator() {
        return new HouseIterator(this);
    }

    public void addCard(Card playerCard) throws RankMismatchException {
        if(playerCard == null) {
            throw new NullPointerException("Cannot add card to house: " + this.toString());
        }

        if(playerCard.getRank().equals(this.getRank())) {
            this.cards.add(playerCard);
        }
        else {
            throw new RankMismatchException();
        }
    }

    public boolean removeChild(House h) {
        if(h != null && this.children != null) {
            return this.children.remove(h);
        }
        return false;
    }

    public void displayCards() {
        System.out.println("Displaying the cards contained by house: " + this.toString());

        for(House h: this) {
            for(Card c: h.getCards()) {
                System.out.println(c);
            }
        }

    }

    @Override
    public int compareTo(House o) {
        return this.getRank().compareTo(o.getRank());
    }
}
