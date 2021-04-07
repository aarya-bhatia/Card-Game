package com.aarya.game.model;

import java.util.Arrays;

public class FloorController {

    Floor floor;

    public FloorController(Floor floor) {
        this.floor = floor;
    }

    /**
     * Finds a house of same rank
     *
     * @param r the rank to look for
     * @return A house or null
     */
    public House findHouse(Rank r) {
        for (House h : floor.getHouses()) {
            if (h.getRank().equals(r)) {
                return h;
            }
        }
        return null;
    }

    /**
     * Removes all cards and houses contained by cardSelector to the floor
     *
     * @param cardSelector the cardSelector
     */
    public void removeMergeItemsFromFloor(CardSelector cardSelector) {
        if (cardSelector.hasCards()) {
            floor.getCards().removeAll(cardSelector.getCards());
            for(Card card: cardSelector.getCards()) {
                floor.getCardContainer().remove(card.getCardPane());
            }
        }
        if (cardSelector.hasHouses()) {
            floor.getHouses().removeAll(cardSelector.getHouses());
            for(House house: cardSelector.getHouses()) {
                floor.getCardContainer().remove(house.getHousePane());
            }
        }
    }

    /**
     * Adds all cards and houses contained by cardSelector to the floor
     *
     * @param cardSelector the cardSelector
     */
    public void addMergeItemsToFloor(CardSelector cardSelector) {
        if (cardSelector.hasCards()) {
            floor.getCards().addAll(cardSelector.getCards());
            for(Card card: cardSelector.getCards()) {
                floor.getCardContainer().put(card.getCardPane());
            }
        }
        if (cardSelector.hasHouses()) {
            floor.getHouses().addAll(cardSelector.getHouses());
            for(House house: cardSelector.getHouses()) {
                floor.getCardContainer().put(house.getHousePane());
            }
        }
    }

    /**
     * Adds the source house and updates the floor state
     *
     * @param source       the source house
     * @param cardSelector the card selector
     */
    public void performMerge(House source, CardSelector cardSelector) {
        House target = new House(source.getRank());
        House residents = findHouse(source.getRank());

        if (residents == null) {
            this.floor.getHouses().add(source);
        } else {
            target.setChildren(Arrays.asList(residents, source));
            residents.setParent(target);
            source.setParent(target);
            this.floor.getHouses().add(target);
        }
        /* Items contained in the source house are removed from the floor */
        removeMergeItemsFromFloor(cardSelector);
    }

    /**
     * Removes the source house and resets floor state
     *
     * @param source       the source house
     * @param cardSelector the card selector
     */
    public void undoMerge(House source, CardSelector cardSelector) {
        /* Items contained in the source house are added to the floor */
        addMergeItemsToFloor(cardSelector);

        if (source.getParent() == null) {
            floor.getHouses().remove(source.getParent());
        } else {
            House parent = source.getParent();
            parent.remove(source);

            /* All residents are also added to the floor */
            for (House house : parent.getChildren()) {
                this.floor.getHouses().add(house);
            }
            for (Card card : parent.getCards()) {
                this.floor.getCards().add(card);
            }
        }
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

    public boolean hasCard(Card target) {
        for (Card card : this.floor.getCards()) {
            if (card == target) {
                return true;
            }
        }
        return false;
    }

    public Card findCard(Rank rank, Suit suit) {
        for(Card card: this.floor.getCards()) {
            if(card.getRank().equals(rank) && card.getSuit().equals(suit)) {
                return card;
            }
        }
        return null;
    }

    /**
     * Searches for the house on the floor
     *
     * @param target the house to look for
     * @return the house if found, or null
     */
    public boolean hasHouse(House target) {
        for (House house : this.floor.getHouses()) {
            if (house == target) {
                return true;
            }
        }
        return false;
    }

    public Floor getFloor() {
        return this.floor;
    }
}
