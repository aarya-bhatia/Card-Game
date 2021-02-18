package com.aarya.game.model;

import java.util.ArrayList;
import java.util.List;

public class FloorController {

    Floor floor;

    public FloorController(Floor floor) {
        this.floor = floor;
    }

    public House findHouse(Rank r) {
        for (House h : floor.getHouses()) {
            if (h.getRank().equals(r)) {
                return h;
            }
        }
        return null;
    }

    public void removeMergeItemsFromFloor(CardSelector cardSelector) {
        if (cardSelector.hasCards()) {
            floor.getCards().removeAll(cardSelector.getCards());
        }
        if (cardSelector.hasHouses()) {
            floor.getHouses().removeAll(cardSelector.getHouses());
        }
    }

    public void addMergeItemsToFloor(CardSelector cardSelector) {
        if (cardSelector.hasCards()) {
            floor.getCards().addAll(cardSelector.getCards());
        }
        if (cardSelector.hasHouses()) {
            floor.getHouses().addAll(cardSelector.getHouses());
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

        List<House> children = new ArrayList<>();

        children.add(source);
        source.setParent(target);

        if (residents != null) {
            children.add(residents);
            residents.setParent(target);
        }

        target.setChildren(children);

        floor.getHouses().add(target);
        removeMergeItemsFromFloor(cardSelector);
    }

    /**
     * Removes the source house and resets floor state
     *
     * @param source       the source house
     * @param cardSelector the card selector
     */
    public void undoMerge(House source, CardSelector cardSelector) {
        addMergeItemsToFloor(cardSelector);
        floor.getHouses().remove(source.getParent());
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

    public boolean hasHouse(House target) {
        for (House house : this.floor.getHouses()) {
            if (house == target) {
                return true;
            }
        }
        return false;
    }
}
