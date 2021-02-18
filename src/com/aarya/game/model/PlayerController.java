package com.aarya.game.model;

public class PlayerController {

    Player player;

    public PlayerController(Player player) {
        this.player = player;
    }

    /**
     * Checks to see if player has card matching the source house
     *
     * @param playerCard the card currently selected is excluded
     * @param source     the source house
     * @return true if player has key otherwise false
     */
    public boolean hasKey(Card playerCard, House source) {
        for (Card card : this.player.getHand()) {
            if (card != playerCard && card.getRank().equals(source.getRank())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param playerCard the selected card
     * @return checks if player has the selected card in hand
     */
    public boolean hasCard(Card playerCard) {
        for (Card card : this.player.getHand()) {
            if (card == playerCard) {
                return true;
            }
        }
        return false;
    }


    /**
     * The card is removed from the hand.
     *
     * @param selectedCard the card
     */
    public void performMerge(Card selectedCard) {
        this.player.remove(selectedCard);
    }

    /**
     * The card is added to the hand
     *
     * @param selectedCard the selected card
     */
    public void undoMerge(Card selectedCard) {
        this.player.add(selectedCard);
    }

    /**
     * Adds the given house to collection
     *
     * @param h the given house
     */
    public void performClaim(House h) {
        this.player.add(h);
    }

    /**
     * Removes the given house from collection
     *
     * @param h given house
     */
    public void undoClaim(House h) {
        this.player.remove(h);
    }

}
