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
        this.houses = houses;
    }

    public void show() {
        this.cards.forEach(card -> System.out.println(card.toString()));

        System.out.println("\n");

        this.houses.forEach(house -> {
            System.out.println(house.toString());
            house.show();
            System.out.println("\n");
        });

        System.out.println("\n");
    }

    private House findHouseWithRank(Rank r) {
        for (House h : this.houses) {
            if (h.getRank().equals(r)) {
                return h;
            }
        }
        return null;
    }

    /**
     * Finds or creates appropriate house to merge with source house
     *
     * @param source source house
     */
    public void performMerge(House source) {
        House target = findHouseWithRank(source.getRank());

        if (target != null) {
            target.add(source);
        } else {
            this.houses.add(source);
        }
    }

    public void undoMerge(House source, CardSelector cardSelector) {
        this.removeHouse(source);

        if (cardSelector.hasCards()) {
            this.cards.addAll(cardSelector.getCards());
        }

        if (cardSelector.hasHouses()) {
            this.houses.addAll(cardSelector.getHouses());
        }
    }

    public void performClaim(CardSelector cardSelector) {

        if (cardSelector.getCards() != null) {
            this.cards.removeAll(cardSelector.getCards());
        }

        if (cardSelector.getHouses() != null) {
            this.houses.removeAll(cardSelector.getHouses());
        }

    }

    public void undoClaim(CardSelector cardSelector) {

        if (cardSelector.getCards() != null) {
            this.cards.addAll(cardSelector.getCards());
        }

        if (cardSelector.getHouses() != null) {
            this.houses.addAll(cardSelector.getHouses());
        }
    }

    private void removeHouse(House h) {
        House parent = h.getParent();

        /*
         * No parent implies that this is the outermost house in the house tree. It must
         * lie on the floor.
         */
        if (parent == null) {
            this.houses.remove(h);
        }
        /*
         * We remove the reference to the child house from its parent. It woudl then be
         * removed from the picture.
         */
        else {
            parent.removeChild(h);
        }
    }
}
