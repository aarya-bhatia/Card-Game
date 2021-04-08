package com.aarya.game.model;

import com.aarya.game.view.CardContainer;
import javafx.scene.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {

    CardContainer cardContainer;

    /*
     * Contains the cards available to play with
     */
    private List<Card> hand;

    /*
     * The number of bonuses awarded to the player
     */
    private int sweeps = 0;

    /*
     * Contains all the houses collected by the player within the root house
     */
    private House collection;

    public Player() {
        this.hand = new ArrayList<>();
        this.cardContainer = new CardContainer();
    }

    public Player(List<Card> hand) {
        this();
        this.setHand(hand);
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public House getCollection() {
        return this.collection;
    }

    public void setCollection(House collection) {
        this.collection = collection;
    }

    public boolean hasCollection() {
        return this.collection != null;
    }

    public int getSweeps() {
        return sweeps;
    }

    public void setSweeps(int sweeps) {
        this.sweeps = sweeps;
    }

    public void setHand(List<Card> cards) {
        this.hand = new ArrayList<>(cards);
        Card.loadCards(this.hand, this.cardContainer);
    }


    public int getPoints() {
        if (this.collection == null) {
            return 0;
        }
        int points = 0;
        for (House house : this.collection) {
            points += house.getPoints();
        }
        return points;
    }

    public String toString() {
        return "Player # " + this.hashCode();
    }

    public void displayCards() {
        System.out.println("[START] " + this + " [START]");

        System.out.println("Hand");

        for (Card card : this.hand) {
            System.out.println(card);
        }


        if(this.collection != null) {
            System.out.println("Collection");
            for (House house : this.collection) {
                house.displayCards();
            }
        }

        System.out.println("[END] " + this + " [END]");
    }

    /**
     * Adds the card to the hand
     *
     * @param card the card to add
     */
    public void add(Card card) {
        if (!this.hand.contains(card)) {
            this.hand.add(card);
        }
    }

    /**
     * Removes the card from hand
     *
     * @param card the card to remove
     */
    public void remove(Card card) {
        this.hand.remove(card);
    }

    /**
     * Adds or sets the house to the collection.
     *
     * @param house the house to add
     */
    public void add(House house) {
        if (!this.hasCollection()) {
            this.collection = house;
        } else {
            this.collection.add(house);
        }
    }

    /**
     * Removes or resets the player collection
     *
     * @param house the house to remove
     */
    public void remove(House house) {
        if (!this.hasCollection()) {
            return;
        }
        if (house.hasParent()) {
            this.collection.remove(house);
        } else {
            house.getParent().remove(house);
        }
    }

    public CardContainer getCardContainer() {
        return this.cardContainer;
    }
}
