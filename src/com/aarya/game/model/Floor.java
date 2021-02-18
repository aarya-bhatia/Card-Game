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
        this.cards = cards;
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

    public House findHouseWithRank(Rank r) {
        for (House h : this.houses) {
            if (h.getRank().equals(r)) {
                return h;
            }
        }
        return null;
    }

    public void removeMergeItemsFromFloor(CardSelector cardSelector) {
        if (cardSelector.hasCards()) {
            this.cards.removeAll(cardSelector.getCards());
        }
        if (cardSelector.hasHouses()) {
            this.houses.removeAll(cardSelector.getHouses());
        }
    }

    public void addMergeItemsToFloor(CardSelector cardSelector) {
        if (cardSelector.hasCards()) {
            this.cards.addAll(cardSelector.getCards());
        }
        if (cardSelector.hasHouses()) {
            this.houses.addAll(cardSelector.getHouses());
        }
    }

    /**
     * Adds the source house and updates the floor state
     *
     * @param source       the source house
     * @param cardSelector the card selector
     */
    public void performMerge(House source, CardSelector cardSelector) throws RankMismatchException {
        House target = findHouseWithRank(source.getRank());

        if (target != null) {
            target.add(source);
        } else {
            this.houses.add(source);
        }

        this.removeMergeItemsFromFloor(cardSelector);
    }

    /**
     * Removes the source house and resets floor state
     *
     * @param source       the source house
     * @param cardSelector the card selector
     */
    public void undoMerge(House source, CardSelector cardSelector) {
        House parent = source.getParent();

        if (parent == null) {
            this.houses.remove(source);
        } else {
            parent.removeChild(source);
        }

        this.addMergeItemsToFloor(cardSelector);
    }

    /**
     * Removes the cards and houses from floor as they are
     * owned by the player who initiated the claim op
     *
     * @param cardSelector the card selector
     */
    public void performClaim(CardSelector cardSelector) {
        removeMergeItemsFromFloor(cardSelector);
    }

    /**
     * Resets the cards and houses to the floor as they
     * are owned by the floor again
     *
     * @param cardSelector the card selector
     */
    public void undoClaim(CardSelector cardSelector) {
        addMergeItemsToFloor(cardSelector);
    }
}
