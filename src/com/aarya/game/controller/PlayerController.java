package com.aarya.game.controller;


import com.aarya.game.model.*;

public class PlayerController {

    /**
     * Checks to see if player has card matching the source house
     *
     * @param playerCard the card currently selected is excluded
     * @param source     the source house
     * @return true if player has key otherwise false
     */
    public static boolean hasKey(Player player, Card playerCard, House source) {
        for (Card card : player.getHand()) {
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
    public static boolean hasCard(Player player, Card playerCard) {
        for (Card card : player.getHand()) {
            if (card == playerCard) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds a card by rank and suit in player's hand
     * @param rank rank
     * @param suit suit
     * @return the card if found
     */
    public static Card findCard(Player player, Rank rank, Suit suit) {
        for(Card card: player.getHand()) {
            if(card.getRank().equals(rank) && card.getSuit().equals(suit)) {
                return card;
            }
        }
        return null;
    }


    /**
     * The card is removed from the hand.
     *
     * @param selectedCard the card
     */
    public static void performMerge(Player player, Card selectedCard) {
        player.remove(selectedCard);
        player.getCardContainer().put(selectedCard.getCardPane());
    }

    /**
     * The card is added to the hand
     *
     * @param selectedCard the selected card
     */
    public static void undoMerge(Player player, Card selectedCard) {
        player.add(selectedCard);
        player.getCardContainer().remove(selectedCard.getCardPane());
    }

    /**
     * Adds the given house to collection
     *
     * @param h the given house
     */
    public static void performClaim(Player player, House h) {
        player.add(h);
    }

    /**
     * Removes the given house from collection
     *
     * @param h given house
     */
    public static void undoClaim(Player player, House h) {
        player.remove(h);
    }

}
